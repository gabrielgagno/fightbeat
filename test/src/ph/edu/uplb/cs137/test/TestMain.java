package ph.edu.uplb.cs137.test;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Dell on 11/17/2014.
 */
public class TestMain {
    public static void main(String args[]) throws Exception{
        //String fileDir = "test/src/ph/edu/uplb/cs137/test/music01.wav";
        String fileDir = "resources/music/music01.wav";
        InputStream au = new FileInputStream(new File(fileDir));
        AudioStream audioStream = new AudioStream(au);
        AudioPlayer.player.start(audioStream);
    }
}
