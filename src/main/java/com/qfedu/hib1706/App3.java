package com.qfedu.hib1706;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.qfedu.hib1706.domain.User;

public class App3 {
	
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			User user = session.get(User.class, "admin");
			session.delete(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
}
