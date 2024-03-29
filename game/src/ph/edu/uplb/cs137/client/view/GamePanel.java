package ph.edu.uplb.cs137.client.view;

import ph.edu.uplb.cs137.client.CommonUtil;
import ph.edu.uplb.cs137.client.Music;
import ph.edu.uplb.cs137.client.Sprite;
import ph.edu.uplb.cs137.client.NetPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dell on 11/18/2014.
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener{
    private String serverName;
    private String name;
    private int canPress;
    private int[] score;
    private Timer timer;
    private Music chosenMusic;
    private int currentMusicIndex;
    private Sprite sprite;
    private double mult=1;
    private int swc=0, swc2=0, ctr, adj=0,dmg=0, hit=0, perfect=0, miss=0, combo=0, player=2;
    private ArrayList<NetPlayer> players;
    private NetPlayer player1 = new NetPlayer();
    private NetPlayer player2 = new NetPlayer();

    public GamePanel(String serverName, String name){
        this.serverName = serverName;
        this.name = name;
        this.canPress = 0;
        //this.keyCatcher = new KeyCatcher();
        this.timer = new Timer(2, this);
        int[] arr = new int[30];
        arr[0] = 600;
        //arr[1] = 850;
        //arr[2] = 1000;
        for(int i=1;i<30;i++){
            arr[i]=arr[i-1]+150;
        }
        //this.addKeyListener(keyCatcher);
        this.addKeyListener(this);
        this.chosenMusic = new Music("music01.wav", "You and I Both", "Jason Mraz", "2003", 30, arr);
        this.sprite=new Sprite();
        this.setPreferredSize(new Dimension(600, 500));
        this.timer.start();
        this.score = new int[this.chosenMusic.getNumMoves()];
        for(int i=0;i<score.length;i++){
            score[i] = -1;
        }
        this.setVisible(true);
        this.setFocusable(true);
        sprite.loadSprites();
        players=new ArrayList<NetPlayer>(2);
        players.add(player1);
        players.add(player2);

    }
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(new Color(225, 182, 80));
        g.fillRect(-1, 390, 600, 100);
        g.setColor(new Color(82, 189, 255));
        g.fillRect(100, 390, 50, 100);
        g.setColor(new Color(34, 37, 255));
        g.fillRect(123, 390, 5, 100);
        g.setColor(new Color(255, 71, 54));
        g.drawRect(105, 5, 170, 30);
        g.drawRect(310+15, 5, 170, 30);
        g.setColor(new Color(255, 3, 54));
        g.fillRect(105, 5, players.get(0).getHealth(), 30);
        g.fillRect(315+10, 5, players.get(1).getHealth(), 30);
        Font font = new Font("Serif", Font.BOLD, 20);
        Font font2 = new Font("Serif", Font.BOLD, 25);
        g.setFont(font);
        g.setColor(new Color(34, 37, 255));
        if(combo>2)
            g.drawString("COMBO "+combo+"!", 20, 135);
        if(combo>=10) {
            g.drawString("MULTIPLIER x" + mult + "!", 20, 155);
        }
        if(hit==1){
            try {
                g.drawImage(ImageIO.read(new File("resources/img/hit.png")), 300, 100, this);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        else if(perfect==1){
            try {
                g.drawImage(ImageIO.read(new File("resources/img/perfect.png")), 300, 100, this);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        else if(miss==1) {
            try {
                g.drawImage(ImageIO.read(new File("resources/img/miss.png")), 300, 100, this);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        if(canPress!=0){
            g.setColor(new Color(255, 71, 54));
            g.drawRect(100, 390, 50, 100);
        }
        if(player==1) {
            g.drawImage(this.sprite.getSonicAvatar(), 500, 5, this);
            g.drawImage(this.sprite.getLuffyAvatar(), 0, 5, this);

            g.drawImage(this.sprite.getBufferedImageSonicArray(swc2)[this.sprite.getCurrSonic()], 310, 177, this);
            g.drawImage(this.sprite.getBufferedImageLuffyArray(swc)[this.sprite.getCurrLuffy()], 235, 150 + adj, this);
        }
        else{
            g.drawImage(this.sprite.getSonicAvatar(), 0, 5, this);
            g.drawImage(this.sprite.getLuffyAvatar(), 500, 5, this);

            g.drawImage(this.sprite.getBufferedImageSonicFlippedArray(swc)[this.sprite.getCurrSonic()], 235, 177-adj, this);
            g.drawImage(this.sprite.getBufferedImageLuffyFlippedArray(0)[this.sprite.getCurrLuffy()], 310, 150, this);
            //g.drawImage(this.sprite.getBufferedImageSonicArray(swc2)[this.sprite.getCurrSonic()], 310, 177, this);
            //g.drawImage(this.sprite.getBufferedImageLuffyArray(swc)[this.sprite.getCurrLuffy()], 235, 150 + adj, this);
        }
        /*try{
            g.drawImage(ImageIO.read(new File("resources/img/Sonic.png")).getSubimage(5, 2, 27, 35), 100,30,this);
        }
        catch(IOException e) {
            e.printStackTrace();
        }*/
        try{
            for(int i=0;i<this.chosenMusic.getArrowList().size();i++){
                g.drawImage(ImageIO.read(new File("resources/img/" + this.chosenMusic.getArrowList().get(i) + ".png")), this.chosenMusic.getxCoordinates()[i], 420, this);
            }
            //g.drawImage(ImageIO.read(new File("resources/img/down.png")), 100, 420, this);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        g.dispose();
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==timer){
            if(ctr%10==0) {
                this.sprite.setCurrSonic((this.sprite.getCurrSonic() + 1) % 4);
                this.sprite.setCurrLuffy((this.sprite.getCurrLuffy() + 1) % 4);
                if (swc == 1 && ctr == 40){
                    swc = 0;
                    swc2 = 0;
                    adj=0;
                    hit=0;
                    perfect=0;
                    miss=0;
                }
            }
            for(int i=0;i<this.chosenMusic.getArrowList().size();i++){
                this.chosenMusic.getxCoordinates()[i]--;
                if(this.chosenMusic.getxCoordinates()[currentMusicIndex] < 80){
                    if(score[currentMusicIndex] == -1){
                        System.out.println("MISS");
                        combo=0;
                        mult=0;
                        score[currentMusicIndex] = 0;
                    }
                    if(currentMusicIndex+1<this.chosenMusic.getArrowList().size()){
                        currentMusicIndex++;
                    }
                }
            }
            ctr++;
            repaint();
        }
    }

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_UP){
            if(canPress==1){
                canPress=0;
                swc=1;
                ctr=0;
                adj=13;
                this.sprite.setCurrSonic(0);
                this.sprite.setCurrLuffy(0);
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if(canPress==2){
                canPress=0;
                swc=1;
                ctr=0;
                adj=13;
                this.sprite.setCurrSonic(0);
                this.sprite.setCurrLuffy(0);
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(canPress==3){
                canPress=0;
                swc=1;
                ctr=0;
                adj=13;
                this.sprite.setCurrSonic(0);
                this.sprite.setCurrLuffy(0);
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(canPress==4){
                canPress=0;
                swc=1;
                ctr=0;
                adj=13;
                this.sprite.setCurrSonic(0);
                this.sprite.setCurrLuffy(0);
            }
        }
    }

    public void keyTyped(KeyEvent e){

    }

    public void keyPressed(KeyEvent e){
        int res = 0;
        if(canPress==0){
            if(e.getKeyCode() == KeyEvent.VK_UP){
                System.out.println("UP!");
                canPress = 1;
                res = CommonUtil.checker(canPress, this.chosenMusic.getArrowList().get(currentMusicIndex), this.chosenMusic.getxCoordinates()[currentMusicIndex], this.score, currentMusicIndex);
                if(res!=-1){
                    if(res==1)
                        hit=1;
                    else
                        perfect=1;
                    if(players.get(1).getHealth()!=0)
                        players.get(1).setHealth(players.get(1).getHealth()-(res+(combo/10)));
                    //dmg=(dmg+5)%170;
                    this.chosenMusic.getxCoordinates()[currentMusicIndex]=this.chosenMusic.getxCoordinates()[currentMusicIndex]-200;
                    swc2 = 1;
                    combo++;
                    if(combo%10==0)
                        mult+=0.5;
                }
                else {
                    miss = 1;
                    swc2 = 2;
                    combo=0;
                    mult=0;
                }
            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                System.out.println("DOWN!");
                canPress = 2;
                res = CommonUtil.checker(canPress, this.chosenMusic.getArrowList().get(currentMusicIndex), this.chosenMusic.getxCoordinates()[currentMusicIndex], this.score, currentMusicIndex);
                if(res!=-1){
                    if(res==1)
                        hit=1;
                    else
                        perfect=1;
                    if(players.get(1).getHealth()!=0)
                        players.get(1).setHealth(players.get(1).getHealth()-(res+(combo/10)));
                    //dmg=(dmg+5)%170;
                    this.chosenMusic.getxCoordinates()[currentMusicIndex]=this.chosenMusic.getxCoordinates()[currentMusicIndex]-200;
                    swc2 = 1;
                    combo++;
                    if(combo%10==0)
                        mult+=0.5;
                }
                else {
                    miss = 1;
                    swc2 = 2;
                    combo=0;
                    mult=0;
                }
            }
            else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                System.out.println("LEFT!");
                canPress = 3;
                res = CommonUtil.checker(canPress, this.chosenMusic.getArrowList().get(currentMusicIndex), this.chosenMusic.getxCoordinates()[currentMusicIndex], this.score, currentMusicIndex);
                if(res!=-1){
                    if(res==1)
                        hit=1;
                    else
                        perfect=1;
                    if(players.get(1).getHealth()!=0)
                        players.get(1).setHealth(players.get(1).getHealth()-(res+(combo/10)));
                    //dmg=(dmg+5)%170;
                    this.chosenMusic.getxCoordinates()[currentMusicIndex]=this.chosenMusic.getxCoordinates()[currentMusicIndex]-200;
                    swc2 = 1;
                    combo++;
                    if(combo%10==0)
                        mult+=0.5;
                }
                else {
                    miss = 1;
                    swc2 = 2;
                    combo=0;
                    mult=0;
                }
            }
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                System.out.println("RIGHT!");
                canPress = 4;
                res = CommonUtil.checker(canPress, this.chosenMusic.getArrowList().get(currentMusicIndex), this.chosenMusic.getxCoordinates()[currentMusicIndex], this.score, currentMusicIndex);
                if(res!=-1){
                    if(res==1)
                        hit=1;
                    else
                        perfect=1;
                    if(players.get(1).getHealth()!=0)
                        players.get(1).setHealth(players.get(1).getHealth()-(res+(combo/10)));
                    //dmg=(dmg+5)%170;
                    this.chosenMusic.getxCoordinates()[currentMusicIndex]=this.chosenMusic.getxCoordinates()[currentMusicIndex]-200;
                    res=-1;
                    swc2 = 1;
                    combo++;
                    if(combo%10==0)
                        mult+=0.5;
                }
                else {
                    miss = 1;
                    swc2 = 2;
                    combo=0;
                    mult=0;
                }
            }
        }
    }
    /*
    private class KeyCatcher extends KeyAdapter{
        public void keyTyped(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_UP){
                System.out.println("UP!");
            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                System.out.println("DOWN!");
            }
            else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                System.out.println("LEFT!");
            }
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                System.out.println("RIGHT!");
            }
        }
    }*/
}
