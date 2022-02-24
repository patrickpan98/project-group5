// Plugin options and our code
$("#modal_trigger1").leanModal({
    top: 100,
    overlay: 0.6,
    closeButton: ".modal_close"
});

$("#modal_trigger2").leanModal({
    top: 100,
    overlay: 0.6,
    closeButton: ".modal_close"
});

$("#modal_trigger3").leanModal({
    top: 100,
    overlay: 0.6,
    closeButton: ".modal_close"
});


//Pour l'admin
$(function() {
    // Calling Login Form
    $("#login_form").click(function() {
        $(".social_login").hide();
        $(".user_login").show();
        return false;
    });

    // Calling Register Form
    $("#register_form").click(function() {
        $(".social_login").hide();
        $(".user_register").show();
        //$(".header_title").text('Register');
        return false;
    });

    // Going back to Social Forms
    $(".back_btn").click(function() {
        $(".user_login").hide();
        $(".user_register").hide();
        $(".social_login").show();
        //$(".header_title").text('Login');
        return false;
    });
});

//Pour le client
$(function() {
    // Calling Login Form
    $("#login_form_client").click(function() {
        $(".social_login_client").hide();
        $(".user_login_client").show();
        return false;
    });

    // Calling Register Form
    $("#register_form_client").click(function() {
        $(".social_login_client").hide();
        $(".user_register_client").show();

        //$(".header_title").text('Register');
        return false;
    });

    // Going back to Social Forms
    $(".back_btn_client").click(function() {
        $(".user_login_client").hide();
        $(".user_register_client").hide();
        $(".social_login_client").show();

        //$(".header_title").text('Login');
        return false;
    });
});

//Pour le coiffeur
$(function() {
    // Calling Login Form
    $("#login_form_coiffeur").click(function() {
        $(".social_login_coiffeur").hide();
        $(".user_login_coiffeur").show();
        return false;
    });

    // Going back to Social Forms
    $(".back_btn_coiffeur").click(function() {
        $(".user_login_coiffeur").hide();
        $(".social_login_coiffeur").show();

        //$(".header_title").text('Login');
        return false;
    });
});