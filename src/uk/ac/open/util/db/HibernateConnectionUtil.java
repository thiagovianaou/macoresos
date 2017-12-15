package uk.ac.open.util.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateConnectionUtil {

	
	private static SessionFactory sessionFactory;
	
	
	static{
		try {
			AnnotationConfiguration acfg = new AnnotationConfiguration();
			acfg.configure();
			sessionFactory = acfg.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}		
	}
	
	public static Session getSession(){
		return sessionFactory.openSession();
	}
}
