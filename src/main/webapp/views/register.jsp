
<jsp:include page="/pages/layout/header.jsp">
   <jsp:param value="Registration" name="title"/>
   <jsp:param value="js/rulesForRegistration.js" name="js"/>
   <jsp:param value="css/phone.css" name="css"/>
</jsp:include>

	<div class="container" style="width: 30%; background: white; border-radius: 6px;">
	   <form class ="form-horizontal registrationForm">
	      <div class="form-group">
	         <label for="fname">First Name</label>
	         <input id ="fname" name ="fname" type="text" class="form-control" id="first" aria-describedby="emailHelp" placeholder="Enter first name">
	      </div>
	      <div class="form-group">
	         <label for="lname">Last Name</label>
	         <input id = "lname" name ="lname" type="text" class="form-control" id="last" aria-describedby="emailHelp" placeholder="Enter last name">
	      </div>
	      <div class="form-group">
	         <label for="phone">Phone number(format XXXX-XXX-XXXX)</label>
	         <input id="phone" name ="phone" type="text" class="form-control input-medium bfh-phone" type="tel" pattern="^\d{4}-\d{3}-\d{4}$" required id="phone" placeholder="Enter phone number(format XXXX-XXX-XXXX)">
	      </div>
	      <div  class="form-group">
	         <label for="password">Password</label>
	         <input id = "password" name ="password" type="password" class="form-control" id="password" placeholder="Enter password">
	      </div>
	      <div class="form-group">
	         <label for="confirmPassword">Confirm Password</label>
	         <input id = "confirmPasswrod" name = "confirmPassword" type="password" class="form-control" id="confirmPassword" placeholder="Confirm password">
	      </div>
	  
	      <button type="submit" class="btn btn-primary" style ="margin-left: 40%">Submit</button>
	   </form>
	 </div>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.js"></script>
<jsp:include page="/pages/layout/footer.jsp"/>