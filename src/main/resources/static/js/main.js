$(function () {
    $("#nav").load("templates/navigation.html");

    $('#login-form-link').click(function (e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function (e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
});


$("#login-form").submit(function (e) {
    e.preventDefault();

    var email = $("#email-log").val();
    var password = $("#password-log").val();

    var data = {
        "email": email,
        "password": password
    };

    $.ajax({
        type: $(this).attr("method"),
        url: $(this).attr("action"),
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (data, textStatus, request) {
            var token = request.getResponseHeader("Authorization");
            var location = request.getResponseHeader("Location");

            localStorage.setItem("token", token);

            if (location) {
                window.location.replace(location);
            } else {
                window.location.replace("/index.html")
            }
        },
        error: function (err) {
            var json = err.responseJSON;
            alert(json.message);
        }
    })


});