package com.qfedu.hib1706;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.qfedu.hib1706.domain.User;

public class App2 {
	
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			User user = session.get(User.class, "admin");
			System.out.println(user.getPassword());
			System.out.println(user.getEmail());
			user.setEmail("admin@126.com");
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
