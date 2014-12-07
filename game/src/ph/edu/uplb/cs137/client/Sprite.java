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
    private static BufferedImage sonicAvatar;
    private static BufferedImage luffyAvatar;

    private static BufferedImage[] sonic;
    private static BufferedImage[] sonicHit;
    private static BufferedImage[] luffy;
    private static BufferedImage[] luffyPunch;

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
        else
            return sonicHit;
    }

    public BufferedImage[] getBufferedImageLuffyArray(int swc){
        if(swc==0)
            return luffy;
        else
            return luffyPunch;
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
            sonicAvatar = ImageIO.read(new File("resources/img/SonicAvatar.png"));
            luffyAvatar = ImageIO.read(new File("resources/img/LuffyAvatar.png"));
           // luffyAvatar = new BufferedImage(mainImageLuffy.getSubimage(1*widthLuffy+5, 17, widthLuffy, 23))

            sonic = new BufferedImage[frameCount];
            sonicHit = new BufferedImage[frameCount];
            luffy = new BufferedImage[frameCount];
            luffyPunch = new BufferedImage[frameCount];
            //west = new BufferedImage[frameCount];
            //north = new BufferedImage[frameCount];
            //south = new BufferedImage[frameCount];

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
                luffy[i] = mainImageLuffy.getSubimage(i*widthLuffy+5, 17, widthLuffy, heightLuffy);
            }
            luffy[3] = mainImageLuffy.getSubimage(1*widthLuffy+5, 17, widthLuffy, heightLuffy);

            for(int i=0; i<frameCount-2; i++) {
                luffyPunch[i] = mainImageLuffy.getSubimage(i * widthLuffy + 5, 95, widthLuffy, heightLuffy);
            }
            luffyPunch[2] = mainImageLuffy.getSubimage(2 * widthLuffy + 12, 95, 65, heightLuffy);
            luffyPunch[3] = mainImageLuffy.getSubimage(1*widthLuffy+5,95, widthLuffy, heightLuffy);
        }
        catch(Exception e){
            System.out.println("Spritesheet Not Found!");
        }

    }
}
