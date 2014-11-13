package utils;

import java.sql.Time;
import java.util.Random;

public class HockeyUtils {
    
    public static String CorrectingTime(Time time)
    {
        String strTime = time.toString();
        if (strTime.length() == 8)
            strTime = strTime.substring(3);
        return strTime;
    }
    
    public static String CorrectingSeconds(int seconds)
    {   
        if (seconds < 10)
        {
            return "0" + Integer.toString(seconds);
        }
        
        return Integer.toString(seconds);
    }
    
    public static int RandomSeconds() 
    {
        int min = 20;
        int max = 120;
        
        Random rand = new Random();
        return (rand.nextInt((max - min) + 1) + min) * 1000;
    }
    
}
