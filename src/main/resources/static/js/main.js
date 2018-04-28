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

    loadMesta();
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
            var preduzeceId = request.getResponseHeader("PredId");

            localStorage.setItem("token", token);
            localStorage.setItem("preduzeceId", preduzeceId);

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

$("#register-form").submit(function (e) {
    e.preventDefault();

    var naziv = $("#preduzeca-naziv").val();
    var adresa = $("#preduzeca-adresa").val();
    var pib = $("#preduzeca-pib").val();
    var telefon = $("#preduzeca-telefon").val();
    var email = $("#preduzeca-email").val();
    var password = $("#preduzeca-password").val();
    var logo = $("#preduzeca-logo-add").val();
    var mesto = $("#lista-mesta").val();
    var mestoId = mesto.substr(0, mesto.indexOf('.'));

    var data = {
        "naziv": naziv,
        "adresa": adresa,
        "pib": pib,
        "telefon": telefon,
        "email": email,
        "password": password,
        "logo": logo,
        "mestoId": mestoId
    };

    $.ajax({
        type: $(this).attr("method"),
        url: $(this).attr("action"),
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (data, textStatus, request) {
            window.location.replace("/index.html")
        },
        error: function (err) {
            var json = err.responseJSON;
            alert(json.message);
        }
    })
});

function loadMesta() {
    $.ajax({
        type: "GET",
        url: "api/mesto",
        dataType: "json",
        success: function (mesta) {
            svaMesta = mesta;
            mesta.forEach(function (mesto) {
                $('#lista-mesta').append('<option>' + mesto.id + '. ' + mesto.drzava + ', ' + mesto.grad + '</option>');
            });
        }
    });
}