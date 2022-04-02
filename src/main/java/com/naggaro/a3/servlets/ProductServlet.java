package com.naggaro.a3.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.naggaro.a3.dao.FactoryProvider;
import com.naggaro.a3.dao.ProductDao;
import com.naggaro.a3.entities.Product;

@MultipartConfig(maxFileSize = 162342)
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			Product product = new Product();
			product.setTitle(request.getParameter("title"));
			product.setQuantiity(Integer.parseInt(request.getParameter("quantity")));
			product.setSize(Integer.parseInt(request.getParameter("size")));

			Part part = request.getPart("image");
			
			product.setImage(part.getSubmittedFileName());
			
			
			//product save
			ProductDao pdao=new ProductDao(FactoryProvider.getFactory());
			pdao.saveProduct(product);
			
			try {
				
				String path="C:/Users/Varun Waiskar/eclipse-workspace2/a3/src/main/webapp/img/products/"+part.getSubmittedFileName();
				System.out.println(path);
				FileOutputStream fos= new FileOutputStream(path);
				
				InputStream is= part.getInputStream();
				
				
				byte []data=new byte[is.available()];
				is.read(data);
				
				//writing the data
				fos.write(data);
				fos.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
			HttpSession httpsession= request.getSession();
			httpsession.setAttribute("message","Product added successfully:");
			response.sendRedirect("product.jsp");
			return;

		}

}
