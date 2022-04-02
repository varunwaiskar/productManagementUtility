package com.naggaro.a3.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.naggaro.a3.entities.User;

public class UserDao {

	/*
	 * here we are checking existence of user in database, if it exists we return
	 * User Entity that is recieved from database
	 */

	private SessionFactory factory;

	public UserDao(SessionFactory factory) {
		this.factory = factory;
	}

	// getuser by email and password
	public User getUserByEmailAndPassword(String email, String password) {
		User user = null;
		try {

			String query = "from User where email=:e and password=:p";
			Session session = this.factory.openSession();
			Query q = session.createQuery(query);
			q.setParameter("e", email);
			q.setParameter("p", password);
			user = (User) q.uniqueResult();

			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;

	}

}
