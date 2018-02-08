$(document).ready(function(){
    var isSubmitting = false;
    $('#register-btn').on('click', function(e) {
        e.preventDefault();
        if(isSubmitting){
            return;
        }
        isSubmitting =true;
        $this = $(this);
        var post_url = $this.attr('action');
        var post_data = {
            lname : $('#lname').val(),
            fname : $('#fname').val(),
            phone : $('#phone').val(),
            email : $('#email').val(),
            password : $('#password').val()
        };
        $.ajax({
            url: post_url,
            contentType: "application/json",
            data: JSON.stringify(post_data),
            type: 'POST',
            success: function(msg) {
                window.location.replace('/register?success=true');
                isSubmitting=false;
            },
            error: function(jqXHR, exception,errorThrown) {
                if(jqXHR.status == 406){
                    $('#error-unique').text("Your phone is already signed up");
                } else if(jqXHR.status == 401){
                    $('#error-unique').text("Your email is already signed up");
                }
                isSubmitting=false;
            }
        });
    });
});
