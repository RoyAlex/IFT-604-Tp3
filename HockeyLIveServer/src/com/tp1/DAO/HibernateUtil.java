package com.tp1.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			// Crée la SessionFactory
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
		} catch (HibernateException ex) {
			throw new RuntimeException("Configuration Problem : "
					+ ex.getMessage(), ex);
		}
	}

	@SuppressWarnings("rawtypes")
	public static final ThreadLocal session = new ThreadLocal();

	@SuppressWarnings("unchecked")
	public static Session currentSession() throws HibernateException {
		Session s = (Session) session.get();
		// Ouvre une nouvelle Session, si ce Thread n'en a aucune
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	public static void closeSession() throws HibernateException {
		Session s = (Session) session.get();
		session.set(null);
		if (s != null)
			s.close();
	}
}
