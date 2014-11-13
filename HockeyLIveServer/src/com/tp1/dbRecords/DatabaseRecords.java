package com.tp1.dbRecords;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executors;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.tp1.Connection.TamponManager;
import com.tp1.DAO.EquipeDAO;
import com.tp1.DAO.HibernateUtil;
import com.tp1.DAO.JoueurDAO;
import com.tp1.DAO.MatchDAO;
import com.tp1.library.Compteur;
import com.tp1.library.Equipe;
import com.tp1.library.Joueur;
import com.tp1.library.Match;
import com.tp1.library.Penalite;

public class DatabaseRecords {
    
    private static EquipeDAO equipeDAO = new EquipeDAO();
    private static JoueurDAO joueurDAO = new JoueurDAO();
    private static MatchDAO matchDAO = new MatchDAO();
    
    private final String csvPath = "src/com/tp1/dbRecords/";

	private final String csvMatches = csvPath + "matches.csv";
	private final String csvJoueurs = csvPath + "joueurs.csv";
	private final String csvEquipes = csvPath + "equipes.csv";
	private final String csvCompteurs = csvPath + "compteurs.csv";
	private final String csvPenalites = csvPath + "penalites.csv";
	private final String csvSplit = ",";
	
	private BufferedReader br;
	private String line;
	
	private SessionFactory sessionFactory = null;
		
	public void AddDataToDB()
	{
		// Drop existing table (prevent duplicate data)
		DropAllTables();
		
		// Create all tables
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();	
		
		// Add the content into the tables
		CreateEquipes();
		CreateJoueurs();
		CreateMatches();
	//	CreateCompteurs();
		//CreatePenalites();
		
		sessionFactory.close();
	}
	
	/**
	 * Add all the matches
	 * 
	 */
	private void CreateMatches()
	{
		Match match = new Match();
		
		try
		{
		    br = new BufferedReader(new FileReader(csvMatches));
		
		    while ((line = br.readLine()) != null)
		    {
		    	Session session = sessionFactory.openSession();
		    	session.beginTransaction();
		    	
			    String[] matchInfo = line.split(csvSplit);
			    match = new Match();
			    // Set the content of the new match
			    match.setIdMatch(Integer.parseInt(matchInfo[0]));
			    match.setEquipeLocal(equipeDAO.getEquipeByName(matchInfo[1], session));
			    match.setEquipeVisiteur(equipeDAO.getEquipeByName(matchInfo[2], session));
			    match.setMatchTime(new Time(0, Integer.parseInt(matchInfo[3]), 0));
			    match.setNbrPenalite(Integer.parseInt(matchInfo[4]));
			    match.setPointageEquipeLocal(Integer.parseInt(matchInfo[5]));
			    match.setPointageEquipeVisiteur(Integer.parseInt(matchInfo[6]));
			    match.setPeriodeCourante("1");
			    
			    TamponManager.getInstance().AddToTampon(match.getIdMatch(), Executors.newFixedThreadPool(10));
			    
			    session.save(match);
			    session.getTransaction().commit();
			    session.close();
			    
			    System.out.println("A match has been added !");
		    }
		}
		catch(FileNotFoundException ex)
		{
			System.out.println(ex);
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
	}
	
	/**
	 * Create all players
	 * 
	 */
	private void CreateJoueurs()
	{
        Joueur joueur = new Joueur();
		
		try
		{
		    br = new BufferedReader(new FileReader(csvJoueurs));
		
		    while ((line = br.readLine()) != null)
		    {
		    	Session session = sessionFactory.openSession();
		    	session.beginTransaction();
		    	
			    String[] joueurInfo = line.split(csvSplit);
			    
			    // Set the content of the new player
			    joueur.setNomJoueur(joueurInfo[0]);
			    joueur.setEnPenalite(Boolean.parseBoolean(joueurInfo[1]));	   
			    joueur.setEquipe(equipeDAO.getEquipeByName(joueurInfo[2], session));
   
			    session.save(joueur);
			    session.getTransaction().commit();
			    session.close();
			    
			    System.out.println("A player has been added !");
		    }
		}
		catch(FileNotFoundException ex)
		{
			System.out.println(ex);
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
	}
	
	/**
	 * Create all teams
	 * 
	 */
	private void CreateEquipes()
	{
        Equipe equipe = new Equipe();
		
		try
		{
		    br = new BufferedReader(new FileReader(csvEquipes));
		
		    while ((line = br.readLine()) != null)
		    {
		    	Session session = sessionFactory.openSession();
		    	session.beginTransaction();
		    	
			    String[] equipeInfo = line.split(csvSplit);
			    
			    // Set the content of the new team
			    equipe.setNomEquipe(equipeInfo[0]);
   
			    session.save(equipe);
			    session.getTransaction().commit();
			    session.close();
			    
			    System.out.println("A team has been added !");
		    }
		}
		catch(FileNotFoundException ex)
		{
			System.out.println(ex);
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
	}
	
	/**
     * Create goals
     * 
     */
    private void CreateCompteurs()
    {
        Compteur compteur = new Compteur();
        
        try
        {
            br = new BufferedReader(new FileReader(csvCompteurs));
        
            while ((line = br.readLine()) != null)
            {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                
                String[] compteurInfo = line.split(csvSplit);
                
                // Set the content of the new team
                String[] parts = compteurInfo[0].split("-");
                int minutes = Integer.parseInt(parts[0]);
                int secondes = Integer.parseInt(parts[1]);
                
                compteur.setTimeOfGoal(new Time(0, minutes, secondes));
                compteur.setEquipe(equipeDAO.getEquipeByName(compteurInfo[1], session));
                compteur.setJoueur(joueurDAO.getPlayerByName(compteurInfo[2], session));
                compteur.setMatch(matchDAO.getMatch(Integer.parseInt(compteurInfo[3]), session));
   
                session.save(compteur);
                session.getTransaction().commit();
                session.close();
                
                System.out.println("A goal has been added !");
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println(ex);
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
    }
    
    /**
     * Create penalties
     * 
     */
    private void CreatePenalites()
    {
        Penalite penalite = new Penalite();
        
        try
        {
            br = new BufferedReader(new FileReader(csvPenalites));
        
            while ((line = br.readLine()) != null)
            {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                
                String[] penaliteInfo = line.split(csvSplit);
                
                // Set the content of the new team
                String[] parts = penaliteInfo[0].split("-");
                int minutes = Integer.parseInt(parts[0]);
                int secondes = Integer.parseInt(parts[1]);
                
                penalite.setTimeOfPenalite(new Time(0, minutes, secondes));
                penalite.setDuree(Integer.parseInt(penaliteInfo[1]));
                penalite.setEquipe(equipeDAO.getEquipeByName(penaliteInfo[2], session));
                penalite.setJoueur(joueurDAO.getPlayerByName(penaliteInfo[3], session));
                penalite.setMatch(matchDAO.getMatch(Integer.parseInt(penaliteInfo[4]), session));
   
                session.save(penalite);
                session.getTransaction().commit();
                session.close();
                
                System.out.println("A penalty has been added !");
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println(ex);
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
    }
	
	/**
	 * Drop existing tables
	 */
	private void DropAllTables()
	{	
		ArrayList<String> listOfTables = new ArrayList<String>() {{
		    add("compteur");
		    add("penalite");
		    add("paris");
		    add("match");
		    add("joueur");
		    add("equipe");
		}};
		
		Session session = null;
		
		for(Iterator<String> i = listOfTables.iterator(); i.hasNext();)
		{	
			session = HibernateUtil.currentSession();
	    	session.beginTransaction();
	    	
			SQLQuery query = session.createSQLQuery("drop table " + i.next());
			query.executeUpdate();
			
			session.getTransaction().commit();
		}
		
		HibernateUtil.closeSession();
	}
	
	

}
