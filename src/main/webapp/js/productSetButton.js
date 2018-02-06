$(document).ready(function(){
    var btn = $(".btn-secondary");
    $('.btn-secondary').bind("click",function(e){
        e.preventDefault();
        if($('#isAdded').text() == "Item added to card"){
            window.location.replace('/card');
            return;
        }
        var post_url = $(this).attr('action');
        $.ajax({
            url: post_url,
            type: 'POST',
            success: function(msg) {
                btn.text("Go to card");
                $('#isAdded').text("Item added to card");
            },
            error: function(message) {}
        });
    });
});