package com.tp1.DAO;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tp1.library.Compteur;
import com.tp1.library.Match;
import com.tp1.library.Penalite;

public class CompteurDAO {
    @SuppressWarnings("unchecked")
    public List<Compteur> listCompteurs() {
        Session session = HibernateUtil.currentSession();
        Query query = session.createQuery("from Compteur");
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<?> list = query.list();
        HibernateUtil.closeSession();

        return (List<Compteur>) list;
    }

    @SuppressWarnings("unchecked")
    public List<Compteur> getCompteursForAMatch(int matchId) {
        Session session = HibernateUtil.currentSession();
        Query query = session.createQuery("from Compteur where match.id = :matchId");
        query.setParameter("matchId", matchId);
        List<?> list = query.list();
        HibernateUtil.closeSession();

        return (List<Compteur>) list;
    }
    
    @SuppressWarnings("unchecked")
    public int addCompteur(int id,Time time, int idequipe, int idjoueur, int idmatch, int periode) {    
        Session session = HibernateUtil.currentSession();
      
    	Query query = session.createSQLQuery("INSERT INTO compteur(idcompteur,"+
            "timeofgoal, equipe_idequipe, joueur_idjoueur, match_idmatch, periode, dejaafficher) VALUES "+
    			"(:idcompteur, :timeofgoal,:idequipe, :idjoueur, :idmatch, :periode, :dejaafficher)");
    	
    	query.setParameter("idcompteur", id);
    	query.setParameter("timeofgoal", time);
    	query.setParameter("idequipe", idequipe);
    	query.setParameter("idjoueur", idjoueur);
    	query.setParameter("idmatch", idmatch);
    	query.setParameter("periode", periode);
    	query.setParameter("dejaafficher", false);
    	int result = query.executeUpdate();
    	HibernateUtil.closeSession();
        return result;
    }
    
    public void updateCompteur(Compteur compteur) { 
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        session.update(compteur);
        
        tx.commit();
        HibernateUtil.closeSession();
    }
}
