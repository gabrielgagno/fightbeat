package ph.edu.uplb.cs137.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Dell on 11/19/2014.
 */
public abstract class CommonUtil {

    private static boolean[] check = new boolean[30];

    private static String[] directionsList = {"up", "down", "left", "right"};

    public static int randomize(){
        return new Random().nextInt();
    }

    public static int randomize(int ub){
        return new Random().nextInt(ub);
    }

    public static String[] getDirectionsList(){
        return directionsList;
    }

    public static int checker(int key, String shouldPressString, int xCoordinate, int[] score, int currMusicIndex){
        List<String> x = Arrays.asList(directionsList);
        if(xCoordinate<80 || xCoordinate > 100 + 50){
            System.out.println("MISSED");
            score[currMusicIndex] = 0;
            return -1;
            //this is miss by pressing at the wrong place
        }
        else{
            if(key-1==x.indexOf(shouldPressString) && check[currMusicIndex]==false){
                if(xCoordinate > 120 && xCoordinate < 130){
                    //this is perfect
                    System.out.println("PERFECT!");
                    score[currMusicIndex] = 5;
                    check[currMusicIndex]=true;
                    return 2;
            }
                else{
                    score[currMusicIndex] = 3;
                    System.out.println("HIT!");
                    check[currMusicIndex]=true;
                    return 1;
                    //this is awesome
                }
            }
            else{
                //this is miss by pressing the wrong key
                score[currMusicIndex] = 0;
                return -1;
            }
        }
    }

}
