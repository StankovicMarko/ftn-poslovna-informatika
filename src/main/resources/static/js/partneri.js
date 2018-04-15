var sviPartneri;
var svaMesta;

$(document).ready(function () {

    loadPartneri();
    loadPreduzeca();
    loadMesta();

});

function loadPartneri() {
    $.ajax({
        type: "GET",
        url: "api/poslovni-partner",
        dataType: "json",
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
        success: function (mesta) {
            svaMesta = mesta;
            mesta.forEach(function (mesto) {
                $('#lista-mesta-add').append('<option>' + mesto.id + '. ' + mesto.drzava + ', ' + mesto.grad + '</option>');
            });
        }

    });
}

function loadPreduzeca() {
    $.ajax({
        type: "GET",
        url: "api/preduzece",
        dataType: "json",
        success: function (preduzeca) {
            sviPartneri = preduzeca;
            preduzeca.forEach(function (preduzece) {
                $('#partner-preduzece-add').append('<option>' + preduzece.id + '. ' + preduzece.naziv + '</option>');
            });
        }

    });
}

$('#partner-add-form').submit(function (e) {
    e.preventDefault();

    var naziv = $('#partner-naziv-add').val();
    var adresa = $('#partner-adresa-add').val();
    var vrsta = $('#partner-vrsta-add').val();
    var preduzeceIdString = $('#partner-preduzece-add').find(":selected").text();
    var mestoIdString = $('#lista-mesta-add').find(":selected").text();

    var preduzeceId = preduzeceIdString.substr(0, preduzeceIdString.indexOf('.'));
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
        success: function (faktura) {
            faktura.forEach(function (faktura) {
                $('#faktura').append('<tr> <td>' + faktura.brojFakture + '</td> <td>' + faktura.datumFakture + '</td> <td>' + faktura.datumValute + '</td> <td>' + faktura.osnovica + '</td> <td>' + faktura.ukupanPDV + '</td> <td>' + faktura.iznosPlacanja + '</td> <td>' + faktura.statusFakture + '</td> </tr>');
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