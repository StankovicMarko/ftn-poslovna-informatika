var sveStavkeCenovnika;
var sviCenovnici;
var cenovnikId;
var svaPreduzeca;
var preduzeceId;
var svaRoba;
var token;
var page_number = 0;


$(document).ready(function () {

    token = localStorage.getItem('token');
    preduzeceId = localStorage.getItem("preduzeceId");

    if (!token) {
        window.location.replace("/index.html");
    }

    loadCenovnici();

});

function loadCenovnici() {
    var url = "api/cenovnik";
    if (preduzeceId != 1) {
        url = "api/cenovnik/" + preduzeceId;
    }

    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (cenovnik) {
            sviCenovnici = cenovnik;
            cenovnik.forEach(function (cenovnik) {
                $('#lista-cenovnika').append('<option>' + cenovnik.id + '. ' + cenovnik.datumVazenja + '</option>');
            });
        }
    });
}

function loadStavkeCenovnika(cenovnikId, page) {
    $("#stavkeCenovnika").empty();
    $("#page_number").text(page);

    $.ajax({
        type: "GET",
        url: "api/stavka-cenovnika/cenovnik/" + cenovnikId + "?size=3&page=" + page,
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (stavkeCenovnika) {
            sveStavkeCenovnika = stavkeCenovnika;
            stavkeCenovnika.forEach(function (stavke) {
                $('#stavkeCenovnika').append('<tr>  <td style="display:none;">' + stavke.id + '</td><td>' + stavke.nazivRobe + '</td> <td>' + stavke.cena + '</td> </tr>');
            });
        }
    });
}

// ucitaj svu robu, add padajuci meni
function loadRoba() {
    var url = "api/roba";
    if (preduzeceId != 1) {
        url = "api/roba/preduzece/" + preduzeceId;
    }

    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (robe) {
            svaRoba = robe;
            robe.forEach(function (roba) {
                $('#listaRobe-add').append('<option>' + roba.id + '. ' + roba.naziv + '</option>');
            });
        }

    });
}

function onLeftArrowClick() {
    if (page_number > 0) {
        page_number -= 1;
        loadStavkeCenovnika(cenovnikId, page_number);
    }
}

function onRightArrowClick() {
    page_number += 1;
    loadStavkeCenovnika(cenovnikId, page_number);
}

$('#lista-cenovnika').on('change', function (e) {
    e.stopImmediatePropagation()

    $('#stavkeCenovnika').empty();
    //$("#listaRobe-add").empty();
    loadRoba();

    var cenovnikIdString = $(this).find(":selected").text();
    cenovnikId = cenovnikIdString.substr(0, cenovnikIdString.indexOf('.'));
    //console.log(cenovnikId);

    loadStavkeCenovnika(cenovnikId, page_number);

    //console.log(sveStavkeCenovnika);
});

$('#stavkaCenovnika-add-form').submit(function (e) {
    e.preventDefault();


    var cena = $('#stavkaCenovnika-cena-add').val();

    var robaIdString = $('#listaRobe-add').find(":selected").text();
    var robaId = robaIdString.substr(0, robaIdString.indexOf('.'));

    var data = {
        "cena": cena,
        "cenovnikId": cenovnikId,
        "robaId": robaId
    };

    console.log(data);

    $.ajax({
        type: "POST",
        url: "api/stavka-cenovnika",
        data: JSON.stringify(data),
        contentType: "application/json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (response) {
            console.log(response);
            $('#add-stavkaCenovnika').modal('toggle');
            location.reload(true); //reloads from server rather than browser cache
//            alert(response['message']);
        },
        error: function (err) {
            var json = err.responseJSON;
            alert(json['message']);
        }
    });

});

$('#stavkeCenovnika').on('click', 'tr', function () {
    var stavkaCenovnikaId = $(this).children(':first').text();
    $('#edit-stavkaCenovnika').modal('toggle');

    var stavkaCenovnika = sveStavkeCenovnika.find(function (element) {
        return element.id == stavkaCenovnikaId;
    });


    var cena = $('#stavkaCenovnika-cena-edit').val(stavkaCenovnika.cena);
//    $("#stavkeCenovnika").empty();

//     $.ajax({
//      type: "GET",
//      url: "api/stavka-cenovnika/cenovnik/"+cenovnikId,
//      dataType: "json",
//      success: function (cenovnika) {
//             cenovnika.forEach(function (cenovnik) {
//                                     $('#stavkeCenovnika').append('<tr> <td>'+cenovnik.nazivRobe+'</td> <td>'+cenovnik.cena+'</td> </tr>');
//                                 });
//          }});


    // mogucnost submita menjanja podatak i delete brisanja

    $('#stavkaCenovnika-edit-form').submit(function (e) {
        e.preventDefault();


        var data = {
            "cena": cena.val(),
            "cenovnikId": cenovnikId,
            "robaId": stavkaCenovnika.robaId
        };

        console.log(data);

        $.ajax({
            type: "PUT",
            url: "api/stavka-cenovnika/" + stavkaCenovnikaId,
            data: JSON.stringify(data),
            contentType: "application/json",
            beforeSend: function (request) {
                request.setRequestHeader("Authorization", token);
            },
            success: function (response) {
                console.log(response);
                $('#edit-cenovnik').modal('toggle');
                location.reload(true); //reloads from server rather than browser cache
                //            alert(response['message']);
            },
            error: function (err) {
                var json = err.responseJSON;
                alert(json['message']);
            }
        });

    });


    $('#stavkaCenovnika-edit-form').on('click', '.btn-danger', function (e) {
        e.preventDefault();
        e.stopImmediatePropagation();

        if (confirm('Are you sure you want do delete this Stavku Cenovnika?')) {
            $.ajax({
                type: 'DELETE',
                url: "api/stavka-cenovnika/" + stavkaCenovnikaId,
                contentType: "application/json",
                beforeSend: function (request) {
                    request.setRequestHeader("Authorization", token);
                },
                success: function (response) {
                    location.reload(true); //reloads from server rather than browser cache
                },
                error: function (err) {
                    alert("Can't delete this Stavku Cenovnika");
                }
            });
        }
    });

});

//TODO prikaz loga firme (jos jedan td koji ce biti limitrane velicine