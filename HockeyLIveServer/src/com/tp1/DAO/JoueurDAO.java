package com.tp1.DAO;

import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tp1.library.Equipe;
import com.tp1.library.Joueur;

public class JoueurDAO {
    @SuppressWarnings("unchecked")
    public List<Joueur> listJoueurs() {
        Session session = HibernateUtil.currentSession();
        List<Joueur> joueursList = session.createCriteria(Joueur.class).list();
        HibernateUtil.closeSession();

        return joueursList;
    }

    public Joueur getJoueur(int id) {
        Session session = HibernateUtil.currentSession();
        Joueur joueur = (Joueur) session.get(Joueur.class, id);
        HibernateUtil.closeSession();

        return joueur;
    }
    
    /**
     * Retrieve the player by a given name
     * 
     * @param playerName The name of the player
     * @return object Joueur
     */
    public Joueur getPlayerByName(String playerName, Session session)
    {
        //Session session = HibernateUtil.currentSession();
        Query query = session.createQuery("from Joueur where nomJoueur = :nomJoueur");
        query.setParameter("nomJoueur", playerName);
        List<?> list = query.list();
        //HibernateUtil.closeSession();
        
        return (Joueur)list.get(0);
    }
    
    public Joueur getPlayerByTeam(Equipe team)
    {
        Session session = HibernateUtil.currentSession();
        Query query = session.createQuery("from Joueur where equipe_idequipe = :idEquipe");
        query.setParameter("idEquipe", team.getIdEquipe());
        List<?> list = query.list();
        HibernateUtil.closeSession();
        
        Random rand = new Random();
        //return (Joueur)list.get(rand.nextInt(((list.size() -1) - 0) + 1));
        return (Joueur)list.get(0);
    }
}
