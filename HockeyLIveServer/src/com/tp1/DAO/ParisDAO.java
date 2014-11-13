package com.tp1.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.tp1.library.Match;
import com.tp1.library.Paris;

public class ParisDAO {

	@SuppressWarnings("unchecked")
	public List<Paris> listParis() {
		Session session = HibernateUtil.currentSession();
		List<Paris> parisList = session.createCriteria(Paris.class).list();
		HibernateUtil.closeSession();

		return parisList;
	}

	public Paris getParis(int id) {
		Session session = HibernateUtil.currentSession();
		Paris paris = (Paris) session.get(Paris.class, id);
		HibernateUtil.closeSession();

		return paris;
	}
	
	public synchronized Paris getParisFromUserAndMatch(String idUser, Match match) {
		Session session = HibernateUtil.currentSession();
		List<Paris> parisList =  session.createCriteria(Paris.class).add(Restrictions.eq("idUtilisateur", idUser))
				.createCriteria("match").add(Restrictions.eq("idMatch", match.getIdMatch())).list();
		HibernateUtil.closeSession();
		if(!parisList.isEmpty()){
			return parisList.get(0);
		}
		else{
			return null;
		}
	}
	
	public List<Paris> getParisFromMatch(Match match) {
		Session session = HibernateUtil.currentSession();
		List<Paris> parisList =  session.createCriteria("match").add(Restrictions.eq("idMatch", match.getIdMatch())).list();
		HibernateUtil.closeSession();
		return parisList;
	}

	public Paris addParis(Paris paris) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		session.save(paris);

		tx.commit();
		HibernateUtil.closeSession();

		return paris;
	}

	public Paris updateParis(Paris paris) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		session.update(paris);

		tx.commit();
		HibernateUtil.closeSession();

		return paris;
	}

	public void deleteParis(Paris paris) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		session.delete(paris);

		tx.commit();
		HibernateUtil.closeSession();
	}

}
