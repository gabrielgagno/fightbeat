package ph.edu.uplb.cs137.client;

import ph.edu.uplb.cs137.client.view.ChatPanel;
import ph.edu.uplb.cs137.client.view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.net.SocketException;

public class FightBeat extends JFrame{
    private ChatPanel chatPanel;
    private GamePanel gamePanel;
    public FightBeat(String serverAddress, String name) throws SocketException{
        super("FightBeat: " + name);
        Container c = getContentPane();
        chatPanel = new ChatPanel(serverAddress, name);
        gamePanel = new GamePanel(serverAddress, name);
        this.setLayout(new FlowLayout());
        //this.gamePanel.setSize(600, 500);
        //this.chatPanel.setSize(300, 500);
        c.add(gamePanel);
        c.add(chatPanel);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(930, 550);
        this.setVisible(true);
    }

	public static void main(String args[]) throws Exception{
		if (args.length != 2){
			System.out.println("Usage: java -jar circlewars-client <server> <player name>");
			System.exit(1);
		}
		new FightBeat(args[0],args[1]);
	}
}
