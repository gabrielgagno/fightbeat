package ph.edu.uplb.cs137.client.view;

import ph.edu.uplb.cs137.game.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Dell on 11/18/2014.
 */
public class ChatPanel extends JPanel implements Runnable, Constants {
    JTextField typeArea;
    JTextArea dispArea;
    JScrollPane scrollPane;
    String msg;
    String name="Joseph";
    String pname;
    String server="localhost";
    boolean connected=false;
    DatagramSocket socket = new DatagramSocket();
    String serverData;
    BufferedImage offscreen;


    Thread t=new Thread(this);
    public ChatPanel(String serverName, String name) throws SocketException{
        this.server = serverName;
        this.name = name;
        socket.setSoTimeout(100);

        this.typeArea = new JTextField();
        this.dispArea = new JTextArea();
        this.dispArea.setEditable(false);
        this.scrollPane = new JScrollPane(dispArea);
        this.typeArea.addKeyListener(new KeyHandler());
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setPreferredSize(new Dimension(300, 500));
        //this.typeArea.setPreferredSize(new Dimension(600, 200));
        //this.dispArea.setPreferredSize(new Dimension(600, 200));
        this.setLayout(new BorderLayout(5,5));
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(typeArea, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setFocusable(true);
        t.start();
    }

    public void send(String msg){
        try{
            byte[] buf = msg.getBytes();
            InetAddress address = InetAddress.getByName(server);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
            socket.send(packet);
        }catch(Exception e){}

    }

    /**
     * The juicy part!
     */
    public void run(){
        while(true){
            try{
                Thread.sleep(1);
            }catch(Exception ioe){}

            //Get the data from players
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try{
                socket.receive(packet);
            }catch(Exception ioe){/*lazy exception handling :)*/}

            serverData=new String(buf);
            serverData=serverData.trim();

            //if (!serverData.equals("")){
            //	System.out.println("Server Data:" +serverData);
            //}

            //Study the following kids.
            if (!connected && serverData.startsWith("CONNECTED")){
                connected=true;
                System.out.println("Connected.");
            }else if (!connected){
                System.out.println("Connecting..");
                send("CONNECT "+name);
            }else if (connected){
                if (serverData.startsWith("PLAYER")){
                    String[] playersInfo = serverData.split(":");
                    for (int i=0;i<playersInfo.length;i++){
                        String[] playerInfo = playersInfo[i].split(" ");
                        String pname =playerInfo[1];
                        String dispMsg = "";
                        for(int j=2;j<playerInfo.length;j++){
                            dispMsg+=playerInfo[j]+" ";
                        }
                        if(!dispMsg.equals("null ")){
                            dispArea.append("\n" + pname + ": " + dispMsg);
                        }
                        dispArea.selectAll();
                    }
                    this.revalidate();
                }
            }
        }
    }

    /**
     * Repainting method
     */
    public void paintComponent(Graphics g){
        g.drawImage(offscreen, 0, 0, null);
    }

    class KeyHandler extends KeyAdapter {

        public void keyPressed(KeyEvent ke){
            if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                msg = typeArea.getText();
                System.out.println("YAY");
                send("PLAYER " + name + " " + msg);
                typeArea.setText("");
            }
        }
    }
}
