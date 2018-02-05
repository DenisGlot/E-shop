<%@page import="com.denisgl.dao.entities.Product"%>
<jsp:include page="/pages/layout/header.jsp">
   <jsp:param value="Item" name="title"/>
   <jsp:param value="js/productSetButton.js" name="js"/>
   <jsp:param value="css/message.css" name="css"/>
</jsp:include>
	<div class="container" style="width: 70%; background: white; border-radius: 6px;">
		<form>
		    <h1 class="my-4">${product.getName()}</h1>
			<div class="row">
				    <div class="col-md-8"><img class="img-fluid" src="${product.getUrlofimg()}" alt="" width="80%" height="60%"></div>
					<h3 class="my-5">Description</h3>
					<p>${product.getDescription()}</p>
					<h3 class="my-5">Price : ${product.getPrice()}$</h3>
					<h3 class="my-5">Left : ${product.getQuantity()}</h3>
					<h3 class="my-5">Made by : ${suplierName}</h3>
				    <p id="isAdded" class="my-5" style = "color: greenyellow;"></p>
					<button class="btn btn-lg btn-secondary my-5">Add to card</button>
				    <input class="my-3" type="hidden" name="productId" value="${product.productid}">
			</div>
		</form>
	</div>
<jsp:include page="/pages/layout/footer.jsp"/>