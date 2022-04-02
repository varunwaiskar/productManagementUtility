
<%@page import="java.sql.Blob"%>
<%@page import="com.naggaro.a3.entities.*"%>
<%@page import="com.naggaro.a3.dao.*"%>

<%@page import="java.util.List"%>
<html>
<head>
<title>Product Management Tool</title>
<%@include file="components/common_css_js.jsp"%>
<link type="text/css"
		  rel="stylesheet"
		  href="css/style1.css" />
<link type="text/css"
		  rel="stylesheet"
		  href="css/add-customer-style.css">
</head>
<body>
	<%@include file="components/navbar.jsp"%>

	<h2>Image Upload</h2>
	
<div class="card-body">	
	<%@include file="components/message.jsp"%>
	<form action="ProductServlet" method = "post" enctype="multipart/form-data">
	
		Title : <input type = "text" name = "title" /> 	
		<br>
		Quantity : <input type = "text" name = "quantity" /> 	
		<br>
		Size : <input type = "text" name = "size" /> 	
		<br>
		Image : <input type = "file" name = "image" /> 	
		<br>
		<input type = "submit" value = "Upload" /> 	
	
	</form>
	
</div>
<div id="container">
	
		<div id="content">
	<table>
				<tr>
					<th>S No.</th>
					<th>Title</th>
					<th>Quantity</th>
					<th>Size</th>
					<th>Image</th>
					<th>Action</th>
				</tr>
				
				<%
	
				ProductDao dao = new ProductDao(FactoryProvider.getFactory());
				List<Product> list=null;
			
				list =dao.getAllProducts();
				
				Blob img = null;
	      	  	for(Product product:list)
	      	  	{
	      	    %>	
					
					<tr>
						<td> <%=product.getId() %> </td>
						<td>  <%=product.getTitle() %></td>
						<td> <%=product.getQuantiity() %> </td>
						<td> <%=product.getSize() %> </td>
						<td> <img alt="..." style="max-height:200px; max-width:100px; width:auto;" src="img/products/<%= product.getImage()%>" class="card-img-top m-2"></td>
						<td><a href="Delete"
							   onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a></td>
					</tr>

 			 <%		
	      	  	}
	      	  	
	      	  	
	
	      	  	%>
						
			</table>
			</div>
	</div>
				

</body>
</html>
