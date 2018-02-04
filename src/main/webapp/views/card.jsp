<%@page import="java.util.Map.Entry"%>
<%@page import="com.denisgl.dao.entities.Product"%>
<%@page import="com.denisgl.models.ShoppingCard"%>
<jsp:include page="/pages/layout/header.jsp">
   <jsp:param value="Shopping Card" name="title"/>
   <jsp:param value="js/settingQuantitie.js" name="js"/>
   <jsp:param value="css/card.css" name="css"/>
</jsp:include>
		<div class="shopping-cart">
			 <form id ="myForm" method="post">
				<div class="title">Shopping Bag</div>
				<% 
				   ShoppingCard card = (ShoppingCard) session.getAttribute("card");
				   if(card.size()==0) {
			    %>
					<h1 style="text-align:  center;">You haven't chosen anything</h1>
				<% 
				    } else {
						 for (Entry<Product, Integer> orderUnit : card.getProducts().entrySet()) {
							 //Variables
							 Product product = orderUnit.getKey();
							 Integer quantityInMemory = orderUnit.getValue();
							 //
							 out.println("<div id=\"" + product.getProductid() + "\" class=\"item\">");
							 out.println(
									"   <div class=\"buttons\"><span class=\"delete-btn\"></span><span class=\"like-btn\"></span></div>");
							 out.println("   <div class=\"image\"><img src=\"" + product.getUrlofimg() + "\" alt=\"\" width=\"120\" height=\"80\" /></div>");
							 out.println("   <div class=\"description\"><span>" + product.getName() + "</span><span name=\"price\" class = \"priceOfProduct\">"
									+ product.getPrice(), 2 + "$</span></div>");
							 out.println("   <div class=\"quantity\">");
							 out.println("    <button class=\"plus-btn\" type=\"submit\">");
							 // Image plus
							 out.println("        <img src=\"https://designmodo.com/demo/shopping-cart/plus.svg\" alt=\"\" />");
							 out.println("    </button>");
							 out.println("    <input class=\"quantityOfProduct\" type=\"submit\" name=\"quantity\" value=\"" + quantityInMemory + "\">");
							 out.println("    <button class=\"minus-btn\" type=\"submit\">");
							 // Image Minus
							 out.println("      <img src=\"https://designmodo.com/demo/shopping-cart/minus.svg\" alt=\"\" />");
							 out.println("    </button>");
							 out.println("   </div>");
							 out.println("   <div class=\"total-price\">" + product.getPrice(), 2 * quantityInMemory + "$</div>");
							 out.println("</div>");
					  }
			      }
			   %>
				</div>
			</form>
			<form method="get">
		       <button id="submitButton" type="submit" class="btn btn-primary" style ="text-align: center;">Buy it</button>
		    </form>
		</div>
<jsp:include page="/pages/layout/footer.jsp"/>