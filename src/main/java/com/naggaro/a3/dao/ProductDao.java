package com.naggaro.a3.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.naggaro.a3.entities.Product;

public class ProductDao {

	private SessionFactory factory;

	public ProductDao(SessionFactory factory) {
		this.factory = factory;
	}

	public boolean saveProduct(Product product) {
		boolean f = false;

		try {

			Session session = this.factory.openSession();
			Transaction tx = session.beginTransaction();

			session.save(product);
			f = true;

			tx.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			f = false;
		}
		return f;
	}
	public void deleteProduct(int id) {

		// get the current hibernate session
		Session s = this.factory.openSession();
		Transaction tx = s.beginTransaction();

		// delete object with primary key
		Query theQuery = 
				s.createQuery("delete from Product where id=:productId");
		theQuery.setParameter("productId", id);
		
		theQuery.executeUpdate();	
		tx.commit();
		s.close();
	}


	// get all products

	public List<Product> getAllProducts() {
		Session s = this.factory.openSession();
		Query query = s.createQuery("from Product");
		List<Product> list = query.list();
		s.close();
		return list;

	}

	// get all products of given category
	public List<Product> getAllProductsById(int cid) {
		Session s = this.factory.openSession();
		Query query = s.createQuery("from Product as p where p.category.categoryId=:id");
		query.setParameter("id", cid);
		List<Product> list = query.list();
		s.close();
		return list;
	}
	
	
}
