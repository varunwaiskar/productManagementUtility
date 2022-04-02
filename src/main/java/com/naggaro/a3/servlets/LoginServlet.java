package com.naggaro.a3.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.naggaro.a3.dao.FactoryProvider;
import com.naggaro.a3.dao.ProductDao;
import com.naggaro.a3.dao.UserDao;
import com.naggaro.a3.entities.Product;
import com.naggaro.a3.entities.User;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserDao userDao = new UserDao(FactoryProvider.getFactory());
		User user = userDao.getUserByEmailAndPassword(email, password);

		HttpSession httpSession = request.getSession();
		if (user == null) {

			httpSession.setAttribute("message", "*Invalid Details !! Try with another one*");
			response.sendRedirect("login.jsp");
			return;
		} else {

			httpSession.setAttribute("current-user", user);

			if (user.getType().equals("admin")) {
				// admin: admin.jsp
				ProductDao pdao = new ProductDao(FactoryProvider.getFactory());
				List<Product> list = pdao.getAllProducts();
				request.setAttribute("list", list);
				response.sendRedirect("product.jsp");
			} else if (user.getType().equals("normal")) {
				// normal: normal.jsp
				response.sendRedirect("index.jsp");
			} else {
				// out.println("We have not identified user type")

				httpSession.setAttribute("message", "We have not identified user type !!!");
				response.sendRedirect("login.jsp");
				return;
			}

		}

	}

}
