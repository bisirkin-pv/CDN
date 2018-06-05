
$(document).ready(function(){
    $('#logIn_btn').click(function(){
        $.ajax({
            url: '/login',
            type: "POST",
            dataType: 'json',
            data: $('.login-form').serialize(),
            success: function(response){
                if(response.status===200){
                    window.location.replace("/cdn/list");
                }else{
                    $("#error-text").html(response.message);
                }

            },
            error: function(e){
                console.log(e);
            }
        });
    });

    $('#inputPassword').keypress(function(event) {
        if(event.keyCode===13){
            $('#logIn_btn').click();
        }
    });


});