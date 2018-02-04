<%@page import="com.denisgl.dao.entities.Product"%>
<jsp:include page="/pages/layout/header.jsp">
   <jsp:param value="Item" name="title"/>
   <jsp:param value="" name="js"/>
   <jsp:param value="css/message.css" name="css"/>
</jsp:include>
	<div class="container" style="width: 70%; background: white; border-radius: 6px;">
	    <h1 class="my-4">${product.getName()}</h1>
	    <div class="row">
	        <div class="col-md-8"><img class="img-fluid" src="${product.getUrlofimg()}" alt="" width="80%" height="60%"></div>
	        <div class="col-md-4">
	            <h3 class="my-3">Description</h3>
	            <p>${product.getDescription()}</p>
	            <h3 class="my-3">Price : ${product.getPrice()}$</h3>
				<h3 class="my-3">Left : ${product.getQuantity()}</h3>
				<h3 class="my-3">Made by : ${suplierName}</h3>
	            <a class="btn btn-lg btn-secondary" href = "/item?product=1&quantity=1&cart=yes">Add it in cart</a>
	        </div>
	    </div>
	</div>
<jsp:include page="/pages/layout/footer.jsp"/>