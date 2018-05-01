var sviPartneri;
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

    loadPartneri(page_number);
    loadMesta();

});

function loadPartneri(page) {
    $("#partneri").empty();
    $("#page_number").text(page);

    var url = "api/poslovni-partner";
    if (preduzeceId != 1) {
        url = "api/poslovni-partner/preduzece/" + preduzeceId;
    }

    $.ajax({
        type: "GET",
        url: url + "?size=3&page=" + page,
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (partneri) {
            sviPartneri = partneri;
            partneri.forEach(function (partner) {
                $('#partneri').append('<tr> <td style="display:none;">' + partner.id + '</td> <td>' + partner.naziv + '</td> <td>' + partner.adresa + '</td> <td>' + partner.vrsta + '</td> <td>');
            });
        }

    });
}

function loadMesta() {
    $.ajax({
        type: "GET",
        url: "api/mesto",
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (mesta) {
            svaMesta = mesta;
            mesta.forEach(function (mesto) {
                $('#lista-mesta-add').append('<option>' + mesto.id + '. ' + mesto.drzava + ', ' + mesto.grad + '</option>');
            });
        }

    });
}

function onLeftArrowClick() {
    if (page_number > 0) {
        page_number -= 1;
        loadPartneri(page_number);
    }
}

function onRightArrowClick() {
    page_number += 1;
    loadPartneri(page_number);
}

$('#partner-add-form').submit(function (e) {
    e.preventDefault();

    var naziv = $('#partner-naziv-add').val();
    var adresa = $('#partner-adresa-add').val();
    var vrsta = $('#partner-vrsta-add').val();
    var mestoIdString = $('#lista-mesta-add').find(":selected").text();
    var mestoId = mestoIdString.substr(0, mestoIdString.indexOf('.'));

    var data = {
        "naziv": naziv,
        "adresa": adresa,
        "vrsta": vrsta,
        "preduzeceId": preduzeceId,
        "mestoId": mestoId
    };

    $.ajax({
        type: "POST",
        url: "api/poslovni-partner",
        data: JSON.stringify(data),
        contentType: "application/json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (response) {
            console.log(response);
            $('#add-partner').modal('toggle');
            location.reload(true); //reloads from server rather than browser cache
//            alert(response['message']);
        },
        error: function (err) {
            var json = err.responseJSON;
            alert(json['message']);
        }
    });

});

$('#partneri').on('click', 'tr', function () {
    var partnerId = $(this).children(':first').text();
    $('#edit-partner').modal('toggle');

    var partner = sviPartneri.find(function (element) {
        return element.id == partnerId;
    });


    var naziv = $('#partner-naziv-edit').val(partner.naziv);
    var adresa = $('#partner-adresa-edit').val(partner.adresa);
    var vrsta = $('#partner-vrsta-edit').val(partner.vrsta);

    $("#faktura").empty();

    $.ajax({
        type: "GET",
        url: "api/faktura/partner/" + partnerId,
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (faktura) {
            faktura.forEach(function (faktura) {
                $('#faktura').append('<tr> <td>' + faktura.brojFakture + '</td> <td>' + faktura.datumFakture +
                    '</td> <td>' + faktura.datumValute + '</td> <td>' + faktura.osnovica +
                    '</td> <td>' + faktura.ukupanPdv + '</td> <td>' + faktura.iznosZaPlacanje +
                    '</td> <td>' + faktura.status + '</td> </tr>');
            });
        }
    });

    // mogucnost submita menjanja podatak i delete brisanja

    $('#partner-edit-form').submit(function (e) {
        e.preventDefault();

        var data = {
            "naziv": naziv.val(),
            "adresa": adresa.val(),
            "vrsta": vrsta.val(),
            "preduzeceId": partner.preduzeceId,
            "mestoId": partner.mestoId
        };

        $.ajax({
            type: "PUT",
            url: "api/poslovni-partner/" + partnerId,
            data: JSON.stringify(data),
            contentType: "application/json",
            beforeSend: function (request) {
                request.setRequestHeader("Authorization", token);
            },
            success: function (response) {
                console.log(response);
                $('#edit-partner').modal('toggle');
                location.reload(true); //reloads from server rather than browser cache
                //            alert(response['message']);
            },
            error: function (err) {
                var json = err.responseJSON;
                alert(json['message']);
            }
        });

    });


    $('#partner-edit-form').on('click', '.btn-danger', function (e) {
        e.preventDefault();
        if (confirm('Are you sure you want do delete this Partner?')) {
            $.ajax({
                type: 'DELETE',
                url: 'api/poslovni-partner/' + partnerId,
                contentType: "application/json",
                beforeSend: function (request) {
                    request.setRequestHeader("Authorization", token);
                },
                success: function (response) {
                    location.reload(true); //reloads from server rather than browser cache
                },
                error: function (err) {
                    alert("Can't delete Partner that have Fakturu");
                }
            });
        }

    });

});