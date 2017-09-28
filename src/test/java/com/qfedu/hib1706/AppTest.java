package com.qfedu.hib1706;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.qfedu.hib1706.domain.User;


public class AppTest  {
	private static SessionFactory factory;
	
	private Session session;
	
	static {
		factory = new Configuration().configure().buildSessionFactory();
	}
	
	@Before
	public void setUp() {
		session = factory.getCurrentSession();
		session.beginTransaction();
	}
	
	@After
	public void tearDown() {
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void testSaveAndDelete() {
		User user = new User("wangdachui", "121212", "dcwang@qq.com");
		Assert.assertNotNull(session.save(user));
		session.delete(user);
	}
	
	@Test
	public void testGet() {
		User user = session.get(User.class, "admin");
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getEmail(), "admin@qq.com");
		User user2 = session.get(User.class, "wangdachui");
		Assert.assertNull(user2);
	}
	
	@Test(expected = java.lang.Exception.class)
	public void testLoad() {
		User user = session.load(User.class, "admin");
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getEmail(), "admin@qq.com");
		User user2 = session.load(User.class, "wangdachui");
		Assert.assertNull(user2);
	}
	
	@Test
	public void testFindAll() {
		// SQL - Structured Query Language
		// HQL - Hibernate Query Language - OO
		List<User> userList = session.createQuery(
				"from User as u where u.password=:pwd", User.class)
				.setParameter("pwd", "123123")
				.getResultList();
		// Assert.assertEquals(4, userList.size());
		for (User user : userList) {
			System.out.println(user.getUsername());
		}
	}
	
}