package com.tp1.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tp1.library.Equipe;

public class EquipeDAO {

	@SuppressWarnings("unchecked")
	public List<Equipe> listEquipe() {
		Session session = HibernateUtil.currentSession();
		List<Equipe> equipeList = session.createCriteria(Equipe.class).list();
		HibernateUtil.closeSession();

		return equipeList;
	}

	public Equipe getEquipe(int id) {
		Session session = HibernateUtil.currentSession();
		Equipe equipe = (Equipe) session.get(Equipe.class, id);
		HibernateUtil.closeSession();

		return equipe;
	}
	
	/**
     * Retrieve the team by a given name
     * 
     * @param teamName The name of the team
     * @return object Equipe
     */
    public Equipe getEquipeByName(String teamName, Session session)
    {
        Query query = session.createQuery("from Equipe where nomEquipe = :nomEquipe");
        query.setParameter("nomEquipe", teamName);
        List<?> list = query.list();
        
        return (Equipe)list.get(0);
    }
}
