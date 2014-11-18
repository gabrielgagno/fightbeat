package ph.edu.uplb.cs137.client;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Dell on 11/18/2014.
 */
public class Music {
    private String musicDir;
    private String musicTitle;
    private String musicArtist;
    private String musicYear;
    private int numMoves;
    private ArrayList<String> arrowList;
    private int[] xCoordinates;

    public Music(String musicDir, String musicTitle, String musicArtist, String musicYear, int numMoves, int[] xCoordinates) {
        this.musicDir = musicDir;
        this.musicTitle = musicTitle;
        this.musicArtist = musicArtist;
        this.musicYear = musicYear;
        this.numMoves = numMoves;
        this.xCoordinates = xCoordinates;

        this.arrowList = new ArrayList<String>(this.numMoves);
        for(int i=0;i<this.numMoves;i++){
            arrowList.add(CommonUtil.getDirectionsList()[CommonUtil.randomize(4)]);
            System.out.println(arrowList.get(i));

        }
    }

    public String getMusicDir() {
        return musicDir;
    }

    public void setMusicDir(String musicDir) {
        this.musicDir = musicDir;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getMusicArtist() {
        return musicArtist;
    }

    public void setMusicArtist(String musicArtist) {
        this.musicArtist = musicArtist;
    }

    public String getMusicYear() {
        return musicYear;
    }

    public void setMusicYear(String musicYear) {
        this.musicYear = musicYear;
    }

    public int getNumMoves() {
        return numMoves;
    }

    public void setNumMoves(int numMoves) {
        this.numMoves = numMoves;
    }

    public ArrayList<String> getArrowList() {
        return arrowList;
    }

    public void setArrowList(ArrayList<String> arrowList) {
        this.arrowList = arrowList;
    }

    public int[] getxCoordinates() {
        return xCoordinates;
    }

    public void setxCoordinates(int[] xCoordinates) {
        this.xCoordinates = xCoordinates;
    }
}
