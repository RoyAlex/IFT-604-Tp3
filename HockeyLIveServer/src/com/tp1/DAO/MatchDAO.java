package com.tp1.DAO;

import java.sql.Time;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tp1.library.Match;


public class MatchDAO {
	
	@SuppressWarnings("unchecked")
	public List<Match> listMatchs() {
		Session session = HibernateUtil.currentSession();
		String hql = "FROM Match";
		Query query = session.createQuery(hql);
		List<Match> results = (List<Match>)query.list();
		HibernateUtil.closeSession();
        return results;
    }
	
	public Match getMatch(int id) {	
		Session session = HibernateUtil.currentSession();
        Match match = (Match) session.get(Match.class, id);
        HibernateUtil.closeSession();

        return match;
    }
	
	public Match getMatch(int id, Session session) {    
	    return (Match) session.get(Match.class, id);
    }
	
	public Match addMatch(Match match) {	
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		
        session.save(match);
        
        tx.commit();
        HibernateUtil.closeSession();

        return match;
    }
	
	public Match updateMatch(Match match) {	
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		
		session.update(match);
        
        tx.commit();
        HibernateUtil.closeSession();

        return match;
    }
	
	public void deleteMatch(Match match) {	
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		
		session.delete(match);
        
        tx.commit();
        HibernateUtil.closeSession();
    }
	
	public void setMatchTime(Time matchTime, Match match)
	{
	    Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
        
        Query query = session.createQuery("update Match set matchTime = :matchTime "
                + "where idMatch = :idMatch ");
        query.setParameter("matchTime", matchTime);
        query.setParameter("idMatch", match.getIdMatch());
        query.executeUpdate();
        
        tx.commit();
        HibernateUtil.closeSession();
	}
	
	public void setPeriode(int periode, Match match)
	{
	    Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Query query = session.createQuery("update Match set periodeCourante = :periodeCourante "
                + "where idMatch = :idMatch ");
        if (periode <= 3)
        	query.setParameter("periodeCourante", Integer.toString(periode));
        else
        	query.setParameter("periodeCourante", "End");
        query.setParameter("idMatch", match.getIdMatch());
        query.executeUpdate();
        
        tx.commit();
        HibernateUtil.closeSession();
	}
	
	public void setPoitageLocal(int point, Match match)
    {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Query query = session.createQuery("update Match set pointageEquipeLocal = :point "
                + "where idMatch = :idMatch ");
        query.setParameter("point", point);
        query.setParameter("idMatch", match.getIdMatch());
        query.executeUpdate();
        
        tx.commit();
        HibernateUtil.closeSession();
    }
	
	public void setPoitageVisiteur(int point, Match match)
    {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Query query = session.createQuery("update Match set pointageEquipeVisiteur = :point "
                + "where idMatch = :idMatch ");
        query.setParameter("point", point);
        query.setParameter("idMatch", match.getIdMatch());
        query.executeUpdate();
        
        tx.commit();
        HibernateUtil.closeSession();
    }
}
