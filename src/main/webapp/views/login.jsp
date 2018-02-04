
<jsp:include page="/pages/layout/header.jsp">
   <jsp:param value="Sign in" name="title"/>
   <jsp:param value="" name="js"/>
   <jsp:param value="" name="css"/>
</jsp:include>
   <div style="width: 20%;" class ="container"> 
	    <form class="form-signin">
			<h1 class="form-signin-heading text-muted" style = "text-align: center;">Sign In</h1>
			<% 
			if(request.getAttribute("error")!=null) {
			  out.println("<h2 class=\"form-signin-heading text-muted\" style = \"text-align: center; color: red;\">Your login or password is not correct</h2>");
			}
			%>
			<input type="text" id = "phoneOrEmail" name ="phoneOrEmail" class="form-control" placeholder="Phone(7XXX-XXX-XXXX) or email" required>
			<p class="phoneOrEmailError" style ="color: red;"></p>
			<input type="password" id = "password" name ="password" class="form-control" placeholder="Password" required>
			<p class = "passwordError" style ="color: red;"></p>
			<button id ="validateAndSubmit" class="btn btn-lg btn-primary btn-block" type="submit">
				Sign In
			</button>
		</form>
   </div>
   <script type="text/javascript" src="js/phoneEmailPasswordValidator.js"></script>
<jsp:include page="/pages/layout/footer.jsp"/>