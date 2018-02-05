<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/pages/layout/header.jsp">
   <jsp:param value="Shopping Card" name="title"/>
   <jsp:param value="js/setQauntity.js" name="js"/>
   <jsp:param value="css/card.css" name="css"/>
</jsp:include>
    <form id =\"myForm\">
		<div class="shopping-cart">
			<div class="title">Shopping Bag</div>
			<c:choose>
				<c:when test = "${card.size()==0}">
					<h1 style="text-align:  center;">You haven't chosen anything</h1>
				</c:when>
				<c:otherwise>
					<c:forEach items="${card.products}" var="entry">
						<div id="${entry.key.productid}" class="item">
							<div class="image"><img src="${entry.key.urlofimg}" alt="" width="120" height="80" /></div>
							<div class="description"><span>${entry.key.name}</span><span name="price" class = "priceOfProduct">${entry.key.price}$</span></div>
							<div class="quantity">
								<button class="plus-btn" type="submit">
									<img class ="plus-img" src="https://designmodo.com/demo/shopping-cart/plus.svg" alt="" />
								</button>
								<input class="quantityOfProduct" type="submit" name="quantity" value="${entry.value}">
								<button class="minus-btn" type="submit">
								   	<img class ="minus-img" src="https://designmodo.com/demo/shopping-cart/minus.svg" alt="" />
								</button>
							</div>
							<div class="total-price">${entry.key.price * entry.value}$</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<a class= "btn btn-lg" href="/card?buy=yes"></a>
		</div>
    </form>

<jsp:include page="/pages/layout/footer.jsp"/>