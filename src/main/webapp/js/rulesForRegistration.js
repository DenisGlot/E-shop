	$(document).ready(function() {

        jQuery.validator.addMethod("phoneUs", function (phone_number, element) {
            phone_number = phone_number.replace(/\s+/g, "");
            return this.optional(element) || phone_number.length > 9 && phone_number.match(/^\d{4}-\d{3}-\d{4}$/);
        }, "Please specify a phone number as XXXX-XXX-XXXX");

        jQuery.validator.addMethod("noSpace", function(value, element) {
            return value.indexOf(" ") < 0 && value != "";
        }, "No space please and don't leave it empty");

		$(".registrationForm").validate({
			rules : {
				fname : {
					required : true,
					minlength: 3
				},
				lname : {
					required : true,
					minlength: 3
				},
                phone : {
                    required : true,
                    phoneUs : true
                },
                email : {
                    required : true,
                    email : true
                },
				password : {
					required : true,
					minlength: 5,
					noSpace: true
				},
				confirmPassword : {
					required : true,
					minlength: 5,
					equalTo: "#password"
				}
			},
			highlight: function(e){
				$(e).closest(".form-group").removeClass('has-success').addClass('has-error');
			},
			unhighlight: function(e){
				$(e).closest(".form-group").removeClass('has-error').addClass('has-success');
			}
			
		});
	});