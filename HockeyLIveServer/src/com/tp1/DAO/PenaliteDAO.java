package com.tp1.DAO;

import java.sql.Time;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.tp1.library.Compteur;
import com.tp1.library.Penalite;

public class PenaliteDAO {
    
    @SuppressWarnings("unchecked")
    public List<Penalite> listPenalites() {
        Session session = HibernateUtil.currentSession();
        Query query = session.createQuery("from Penalite");
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<?> list = query.list();
        HibernateUtil.closeSession();

        return (List<Penalite>) list;
    }

    @SuppressWarnings("unchecked")
    public List<Penalite> getPenalitesForAMatch(int matchId) {
        Session session = HibernateUtil.currentSession();
        Query query = session.createQuery("from Penalite where match.id = :matchId");
        query.setParameter("matchId", matchId);
        List<?> list = query.list();
        HibernateUtil.closeSession();

        return (List<Penalite>) list;
    }
    
    @SuppressWarnings("unchecked")
    public int addPenalite(int id, Time time, int idmatch, int idequipe, int idjoueur, int duree, int periode) {    
    	Session session = HibernateUtil.currentSession();
       
    	Query query = session.createSQLQuery("INSERT INTO penalite(idpenalite, duree, " +
    			"timeofpenalite, equipe_idequipe, joueur_idjoueur,"+ 
    			"match_idmatch, periode) VALUES "+
    			"(:idpenalite, :duree, :timeOfPenalite,:idequipe, :idjoueur, :idmatch, :periode)");
    	
    	query.setParameter("idpenalite", id);
    	query.setParameter("duree", duree);
    	query.setParameter("timeOfPenalite", time);
    	query.setParameter("idequipe", idequipe);
    	query.setParameter("idjoueur", idjoueur);
    	query.setParameter("idmatch", idmatch);
    	query.setParameter("periode", periode);
    	int result = query.executeUpdate();
    	HibernateUtil.closeSession();
        return result;
    }
}
