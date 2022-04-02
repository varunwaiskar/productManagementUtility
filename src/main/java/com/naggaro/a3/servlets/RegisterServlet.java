package com.naggaro.a3.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.naggaro.a3.dao.FactoryProvider;
import com.naggaro.a3.entities.User;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			response.setContentType("text/html");

			String userName = request.getParameter("user_name");
			String userEmail = request.getParameter("user_email");
			String userPassword = request.getParameter("user_password");
			String userType = request.getParameter("user_type");

			// creating user object to store edata
			User user = new User(userName, userEmail, userPassword, userType);

			Session hibernateSession = FactoryProvider.getFactory().openSession();

			Transaction tx = hibernateSession.beginTransaction();

			int userId = (Integer) hibernateSession.save(user);

			tx.commit();
			hibernateSession.close();
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("message", "*Registration SuccessFul !! User id is:" + userId);
			response.sendRedirect("register.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
