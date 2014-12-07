package ph.edu.uplb.cs137.client;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
/**
 * Created by John on 12/1/2014.
 */
public class Sprite {
    //private static int widthSonic = 34;
    //private static int heightSonic = 35;
    private static int widthSonic = 28;
    private static int heightSonic = 35;
    private static int widthLuffy = 38;
    private static int heightLuffy = 64;

    private int currSonic=0;
    private int currLuffy=0;

    //public static final int frameCountSonic = 4;
    public static final int frameCount = 4;

    private static BufferedImage mainImageSonic;
    private static BufferedImage mainImageLuffy;
    private static BufferedImage mainImageSonicFlipped;
    private static BufferedImage mainImageLuffyFlipped;
    private static BufferedImage sonicAvatar;
    private static BufferedImage luffyAvatar;

    private static BufferedImage[] sonic;
    private static BufferedImage[] sonicHit;
    private static BufferedImage[] sonicMiss;
    private static BufferedImage[] luffy;
    private static BufferedImage[] luffyPunch;

    private static BufferedImage[] sonicFlipped;
    private static BufferedImage[] sonicFlippedAttack;
    private static BufferedImage[] luffyFlipped;
    private static BufferedImage[] luffyFlippedHit;
    private static BufferedImage[] luffyFlippedMiss;


    public BufferedImage getSonicAvatar(){
        return sonicAvatar;
    }

    public BufferedImage getLuffyAvatar(){
        return luffyAvatar;
        //return new ImageIcon(Toolkit.getDefaultToolkit().createImage(sonic[0].getSource()));
    }

    public BufferedImage[] getBufferedImageSonicArray(int swc){
        if(swc==0)
            return sonic;
        else if(swc==1)
            return sonicHit;
        else
            return sonicMiss;
    }

    public BufferedImage[] getBufferedImageLuffyArray(int swc){
        if(swc==0)
            return luffy;
        else
            return luffyPunch;
    }

    public BufferedImage[] getBufferedImageSonicFlippedArray(int swc){
        if(swc==0)
            return sonicFlipped;
        else
            return sonicFlippedAttack;
    }

    public BufferedImage[] getBufferedImageLuffyFlippedArray(int swc){
        if(swc==0)
            return luffyFlipped;
        else if (swc==1)
            return luffyFlippedHit;
        else
            return luffyFlippedMiss;

    }

    public int getCurrSonic(){
        return currSonic;
    }

    public int getCurrLuffy(){
        return currLuffy;
    }

    public void setCurrSonic(int curr){
        this.currSonic=curr;
    }

    public void setCurrLuffy(int curr){
        this.currLuffy=curr;
    }

    public static void loadSprites(){

        try{

            mainImageSonic = ImageIO.read(new File("resources/img/Sonic.png"));
            mainImageLuffy = ImageIO.read(new File("resources/img/MonkeyDLuffy.png"));
            mainImageSonicFlipped = ImageIO.read(new File("resources/img/SonicFlipped.png"));
            mainImageLuffyFlipped = ImageIO.read(new File("resources/img/LuffyFlipped.png"));

            sonicAvatar = ImageIO.read(new File("resources/img/SonicAvatar.png"));
            luffyAvatar = ImageIO.read(new File("resources/img/LuffyAvatar.png"));
           // luffyAvatar = new BufferedImage(mainImageLuffy.getSubimage(1*widthLuffy+5, 17, widthLuffy, 23))

            sonic = new BufferedImage[frameCount];
            sonicHit = new BufferedImage[frameCount];
            sonicMiss = new BufferedImage[frameCount];
            luffy = new BufferedImage[frameCount];
            luffyPunch = new BufferedImage[frameCount];

            sonicFlipped = new BufferedImage[frameCount];
            sonicFlippedAttack = new BufferedImage[frameCount];
            luffyFlipped = new BufferedImage[frameCount];
            luffyFlippedHit = new BufferedImage[frameCount];
            luffyFlippedMiss = new BufferedImage[frameCount];


            /*for(int i=0; i<frameCount; i++){
                sonic[i] = mainImageSonic.getSubimage(i*widthSonic+270, 168, widthSonic, heightSonic);
            }*/

            for(int i=0; i<frameCount-1; i++) {
                sonic[i] = mainImageSonic.getSubimage(i * widthSonic + 4, 2, widthSonic, heightSonic);
            }
            sonic[3] = mainImageSonic.getSubimage(1 * widthSonic + 4, 2, widthSonic, heightSonic);

            for(int i=0; i<frameCount-1; i++){
                sonicHit[i] = mainImageSonic.getSubimage(i*widthLuffy+5, 500, widthLuffy, heightSonic+2);
            }
            sonicHit[3] = mainImageSonic.getSubimage(1*widthLuffy+5, 500, widthLuffy, heightSonic+2);

            for(int i=0; i<frameCount-1; i++){
                sonicMiss[i] = mainImageSonic.getSubimage(i*(widthSonic+4)+10, 800, widthSonic+4, heightSonic+2);
            }
            sonicMiss[3] = mainImageSonic.getSubimage(1*(widthSonic+4)+10, 800, widthSonic+4, heightSonic+2);

            for(int i=0; i<frameCount-1; i++){
                luffy[i] = mainImageLuffy.getSubimage(i*widthLuffy+5, 17, widthLuffy, heightLuffy);
            }
            luffy[3] = mainImageLuffy.getSubimage(1*widthLuffy+5, 17, widthLuffy, heightLuffy);

            for(int i=0; i<frameCount-2; i++) {
                luffyPunch[i] = mainImageLuffy.getSubimage(i * widthLuffy + 5, 95, widthLuffy, heightLuffy);
            }
            luffyPunch[2] = mainImageLuffy.getSubimage(2 * widthLuffy + 12, 95, 65, heightLuffy);
            luffyPunch[3] = mainImageLuffy.getSubimage(1*widthLuffy+5,95, widthLuffy, heightLuffy);

            for(int i=0; i<frameCount-1; i++) {
                sonicFlipped[i] = mainImageSonicFlipped.getSubimage(734-((1+i) * widthSonic), 2, widthSonic, heightSonic);
            }
            sonicFlipped[3] = mainImageSonicFlipped.getSubimage(734-(2 * widthSonic), 2, widthSonic, heightSonic);

            for(int i=0; i<frameCount-1; i++){
                luffyFlipped[i] = mainImageLuffyFlipped.getSubimage(767-((1+i)  *widthLuffy), 17, widthLuffy, heightLuffy);
            }
            luffyFlipped[3] = mainImageLuffyFlipped.getSubimage(767-(2*widthLuffy), 17, widthLuffy, heightLuffy);

            for(int i=0; i<frameCount-1; i++) {
                sonicFlippedAttack[i] = mainImageSonicFlipped.getSubimage(i * (620-583) + 583, 2326, 620-583, heightLuffy);
            }
            //sonicFlippedAttack[2] = mainImageSonicFlipped.getSubimage(2 * widthLuffy + 12, 95, 65, heightLuffy);
            sonicFlippedAttack[3] = mainImageSonicFlipped.getSubimage(1*(620-583)+583 ,2326, 620-583, heightLuffy);
        }
        catch(Exception e){
            System.out.println("Spritesheet Not Found!");
        }

    }
}
