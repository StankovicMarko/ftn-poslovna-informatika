var svaMesta;
var token;
var preduzeceId;
var page_number = 0;

$(document).ready(function () {

    token = localStorage.getItem('token');
    preduzeceId = localStorage.getItem("preduzeceId");

    if (!token) {
        window.location.replace("/index.html");
    }

    loadMesta(page_number);

});

function loadMesta(page) {
    $("#mesta").empty();
    $("#page_number").text(page);

    $.ajax({
        type: "GET",
        url: "api/mesto?size=3&page=" + page,
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (mesta) {
            svaMesta = mesta;
            mesta.forEach(function (mesto) {
                $('#mesta').append('<tr> <td style="display:none;">' + mesto.id + '</td> <td>' + mesto.grad + '</td> <td>' + mesto.drzava + '</td> </tr>');
            });
        }

    });
}

function onLeftArrowClick() {
    if (page_number > 0) {
        page_number -= 1;
        loadMesta(page_number);
    }
}

function onRightArrowClick() {
    page_number += 1;
    loadMesta(page_number);
}


$('#mesto-add-form').submit(function (e) {
    e.preventDefault();

    var grad = $('#mesto-grad-add').val();
    var drzava = $('#mesto-drzava-add').val();

    var data = {
        "grad": grad,
        "drzava": drzava
    };

    $.ajax({
        type: "POST",
        url: "api/mesto",
        data: JSON.stringify(data),
        contentType: "application/json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (response) {
            console.log(response);
            $('#add-mesto').modal('toggle');
            location.reload(true); //reloads from server rather than browser cache
//            alert(response['message']);
        },
        error: function (err) {
            var json = err.responseJSON;
            alert(json['message']);
        }
    });

});

$('#mesta').on('click', 'tr', function () {
    var mestoId = $(this).children(':first').text();
    $('#edit-mesto').modal('toggle');

    var mesto = svaMesta.find(function (element) {
        return element.id == mestoId;
    });

    var grad = $('#mesto-grad-edit').val(mesto.grad);
    var drzava = $('#mesto-drzava-edit').val(mesto.drzava);
    $("#preduzeca").empty();
    $("#poslovniPartner").empty();

    $.ajax({
        type: "GET",
        url: "api/poslovni-partner/preduzece/" + preduzeceId + "/mesto/" + mestoId,
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (poslovniPartneri) {
            poslovniPartneri.forEach(function (poslovniPartner) {
                $('#poslovniPartner').append('<tr> <td>' + poslovniPartner.naziv +
                    '</td> <td>' + poslovniPartner.adresa +
                    '</td> <td>' + poslovniPartner.vrsta + '</td>  </tr>');
            });
        }
    });


    // mogucnost submita menjanja podatak i delete brisanja

    $('#mesto-edit-form').submit(function (e) {
        e.preventDefault();

        var data = {
            "grad": grad.val(),
            "drzava": drzava.val()
        };

        $.ajax({
            type: "PUT",
            url: "api/mesto/" + mestoId,
            data: JSON.stringify(data),
            contentType: "application/json",
            beforeSend: function (request) {
                request.setRequestHeader("Authorization", token);
            },
            success: function (response) {
                console.log(response);
                $('#edit-mesto').modal('toggle');
                location.reload(true); //reloads from server rather than browser cache
                //            alert(response['message']);
            },
            error: function (err) {
                var json = err.responseJSON;
                alert(json['message']);
            }
        });

    });


    $('#mesto-edit-form').on('click', '.btn-danger', function (e) {
        e.preventDefault();

        if (confirm('Are you sure you want do delete this Mesto?')) {
            $.ajax({
                type: 'DELETE',
                url: 'api/mesto/' + mestoId,
                contentType: "application/json",
                beforeSend: function (request) {
                    request.setRequestHeader("Authorization", token);
                },
                success: function (response) {
                    location.reload(true); //reloads from server rather than browser cache
                },
                error: function (err) {
                    alert("Can't delete Mesto that have Preduzeca");
                }
            });
        }
    });

});

//TODO prikaz loga firme (jos jedan td koji ce biti limitrane velicine