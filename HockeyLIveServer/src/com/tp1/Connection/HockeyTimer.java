package com.tp1.Connection;

import java.sql.Time;
import java.util.Random;

import com.tp1.DAO.CompteurDAO;
import com.tp1.DAO.JoueurDAO;
import com.tp1.DAO.MatchDAO;
import com.tp1.DAO.PenaliteDAO;
import com.tp1.library.Compteur;
import com.tp1.library.Match;
import com.tp1.library.Penalite;


public class HockeyTimer {
    
    private static MatchDAO matchDAO = new MatchDAO();
    private static JoueurDAO joueurDAO = new JoueurDAO();
    private static CompteurDAO compteurDAO = new CompteurDAO();
    private static PenaliteDAO penaliteDAO = new PenaliteDAO();
    private Match match;
    private final int PERIOD_TIME = 1;
    private int goal = 15;
    private int penalite = 12;
    private static int idpenalite = 1;
    private static int idcompteur = 1;
    
    public HockeyTimer(Match match)
    {
        this.match = match;
    }
    
    public void StartTimer() throws InterruptedException
    {
        StartTimer(1);
    }
    
    private void StartTimer(int currentPeriod) throws InterruptedException
    {
        int timePerPeriod = PERIOD_TIME * 60; // Convert to seconds
        //int timePerPeriod = 10; // Convert to seconds
        long delay = timePerPeriod * 1000; // Convert to minutes
    
        do
        {
            int minutes = timePerPeriod / 60;
            int seconds = timePerPeriod % 60;
            
            SaveTime(minutes, seconds);
            
            Thread.sleep(1000);
            timePerPeriod = timePerPeriod - 1;
            delay = delay - 1000;
        } 
        while (delay != 0);
           
        currentPeriod = currentPeriod + 1;
        penalite = 15;
        goal = 12;
        SetPeriode(currentPeriod);   
        
        if (currentPeriod <= 3)
        {     
            StartTimer(currentPeriod);
        }
    }
    
    private void SaveTime(int minutes, int seconds)
    {
        @SuppressWarnings("deprecation")
		Time matchTime = new Time(0, minutes, seconds);
        matchDAO.setMatchTime(matchTime, match);
        
        GenerateGoalsAndPenalties(minutes, seconds, matchTime);       
    }
    
    private void GenerateGoalsAndPenalties(int minutes, int seconds, Time matchTime)
    {
        if(minutes <= goal){
            Random rand = new Random();
            //But à la minute X
            int randGoal = rand.nextInt((20 - 0) + 1);
            if(minutes == randGoal){
                int idequipe = 0;
                int idjoueur = 0;
                //Selon un nombre pair ou impair c'est l'équipe visiteur ou locale
                if(randGoal % 2 == 0){
                    match.setPointageEquipeLocal(match.getPointageEquipeLocal()+1);
                    matchDAO.setPoitageLocal(match.getPointageEquipeLocal(), match);
                  
                    idequipe = match.getEquipeLocal().getIdEquipe();
                    idjoueur = joueurDAO.getPlayerByTeam(match.getEquipeLocal()).getIdJoueur();
                }
                else{
                    match.setPointageEquipeVisiteur(match.getPointageEquipeVisiteur()+1);
                    matchDAO.setPoitageVisiteur(match.getPointageEquipeVisiteur(), match);
                   
                    idequipe = match.getEquipeVisiteur().getIdEquipe();
                    idjoueur = joueurDAO.getPlayerByTeam(match.getEquipeVisiteur()).getIdJoueur();
                }

                compteurDAO.addCompteur(idcompteur, matchTime,idequipe,idjoueur,match.getIdMatch(), Integer.parseInt(match.getPeriodeCourante()));
                idcompteur+=1;
                //matchDAO.updateMatch(match);
                
                goal = minutes - 5;
                
            }
            
        }
        
        if(minutes <= penalite){
            Random rand = new Random();
          //Penalite à la minute X
            int randGoal = rand.nextInt((20 - 0) + 1);
            
            if(minutes == randGoal){
               int idequipe = 0;
               int idjoueur = 0;
               //Selon un nombre pair ou impair c'est l'équipe visiteur ou locale
                if(randGoal % 2 == 0){
                    idequipe = match.getEquipeLocal().getIdEquipe();
                    idjoueur = joueurDAO.getPlayerByTeam(match.getEquipeLocal()).getIdJoueur();
                }
                else{
                     idequipe = match.getEquipeVisiteur().getIdEquipe();
                     idjoueur = joueurDAO.getPlayerByTeam(match.getEquipeVisiteur()).getIdJoueur();
                }
               
                penaliteDAO.addPenalite(idpenalite, matchTime,match.getIdMatch(),idequipe, idjoueur,2, Integer.parseInt(match.getPeriodeCourante()));
                idpenalite+=1;
                //match.setNbrPenalite(match.getNbrPenalite()+1);
                //matchDAO.updateMatch(match);
                penalite = minutes - 8;          
            }
            
        }

    }
    
    private void SetPeriode(int currentPeriod)
    {
        matchDAO.setPeriode(currentPeriod, match);
    }
}
