package com.tp1.Connection;


import utils.HockeyUtils;

import com.tp1.DAO.MatchDAO;
import com.tp1.library.Match;

public class TimerThread extends Thread {
    
    private HockeyTimer timer;
    private Match match;
    
    public TimerThread(Match match)
    {
        //new MatchDAO();
        this.match = match;
        timer = new HockeyTimer(this.match);
        
    }
    
    @Override
    public void run()
    {
        try
        {
            // All game have a delay except the first match for tests purpose
            if (match.getIdMatch() != 1)
                Thread.sleep(HockeyUtils.RandomSeconds());
            
            timer.StartTimer();
        }
        catch (InterruptedException ex)
        {
            System.out.println(ex);
        }
    }

}
