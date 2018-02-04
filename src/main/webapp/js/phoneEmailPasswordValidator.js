function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}

function validatePhoneNumber(phone){
	var re = /^\d{4}-\d{3}-\d{4}$/;
	return re.test(phone);
}

function validate() {
  var phoneOrEmail = $("#phoneOrEmail").val();
  var password = $("#password").val();
  if(isEmptyOrSpaces(password)) {
	  $(".passwordError").text("password is not valid ");
  }
  if(isEmail(phoneOrEmail)){
	  if (validateEmail(phoneOrEmail)) {
		  wrongPasswordOrSubmit(password);
	  } else {
		  $(".phoneOrEmailError").text(phoneOrEmail + " is not valid email");
	  }
  } else {
	  if (validatePhoneNumber(phoneOrEmail)) {
		  wrongPasswordOrSubmit(password);
	  } else {
		  $(".phoneOrEmailError").text(phoneOrEmail + " is not valid phoneNumber");
	  }
  }
  return false;
}

function isEmail(phoneOrEmail){
	return phoneOrEmail.indexOf("@") != -1;
}

function wrongPasswordOrSubmit(password) {
	$(".phoneOrEmailError").text("");
	if(isEmptyOrSpaces(password)) {
		  $(".passwordError").text("password is not valid ");
	  } else {
	      $( ".form-signin" ).submit();
	  }
}

function isEmptyOrSpaces(password) {
	if(password.length === 0){
		return true;
	} else if(password.indexOf(" ") != -1){
		return true;
	} else {
		return false;
	}
}


$("#validateAndSubmit").bind("click", validate);