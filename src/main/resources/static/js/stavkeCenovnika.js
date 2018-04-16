
    var sveStavkeCenovnika;

    var sviCenovnici;
    var cenovnikId;

    var svaPreduzeca;
    var preduzeceId;

    var svaRoba;






$(document).ready(function () {
     loadPreduzeca();
    //loadRoba();

});


function loadPreduzeca() {
    $.ajax({
        type: "GET",
        url: "api/preduzece",
        dataType: "json",
        success: function (preduzeca) {
                svaPreduzeca=preduzeca;
                preduzeca.forEach(function (preduzece) {
                    $('#lista-preduzeca').append('<option>'+preduzece.id+'. '+preduzece.naziv+'</option>');
                });
            }
    });
}
function loadCenovnici(preduzeceId) {
    $.ajax({
        type: "GET",
        url: "api/cenovnik/"+preduzeceId,
        dataType: "json",
        success: function (cenovnik) {
                sviCenovnici=cenovnik;
                cenovnik.forEach(function (cenovnik) {
                    $('#lista-cenovnika').append('<option>'+cenovnik.id+'. '+cenovnik.datumVazenja+'</option>');
                });
            }
    });
}

function loadStavkeCenovnika(cenovnikId) {
    $.ajax({
        type: "GET",
        url: "api/stavka-cenovnika/cenovnik/"+cenovnikId,
        dataType: "json",
        success: function (stavkeCenovnika) {
                sveStavkeCenovnika=stavkeCenovnika;
                stavkeCenovnika.forEach(function (stavke) {
                    $('#stavkeCenovnika').append('<tr>  <td style="display:none;">' + stavke.id+ '</td><td>' + stavke.nazivRobe+ '</td> <td>'+stavke.cena+'</td> </tr>');
                });
            }
    });
}
// ucitaj svu robu, add padajuci meni
function loadRoba() {
    $.ajax({
        type: "GET",
        url: "api/roba",
        dataType: "json",
        success: function (robe) {
                svaRoba=robe;
                robe.forEach(function (roba) {
                    $('#listaRobe-add').append('<option>' + roba.id+ '. '+roba.naziv+'</option>');
                });
            }

    });
}

$('#lista-preduzeca').on('change', function(e) {
e.stopImmediatePropagation()
        $('#lista-cenovnika').empty();
        $('#lista-cenovnika').append('<option value="" selected disabled hidden>Izaberi datum važenja cenovnika</option>');
        $('#stavkeCenovnika').empty();

     var preduzeceIdString = $(this).find(":selected").text();
     preduzeceId = preduzeceIdString.substr(0, preduzeceIdString.indexOf('.'));
      loadCenovnici(preduzeceId);
});

$('#lista-cenovnika').on('change', function(e) {
e.stopImmediatePropagation()

    $('#stavkeCenovnika').empty();
    //$("#listaRobe-add").empty();
        loadRoba();

     var cenovnikIdString = $(this).find(":selected").text();
     cenovnikId = cenovnikIdString.substr(0, cenovnikIdString.indexOf('.'));
    //console.log(cenovnikId);

      loadStavkeCenovnika(cenovnikId);

      //console.log(sveStavkeCenovnika);
});

$('#stavkaCenovnika-add-form').submit(function (e) {
    e.preventDefault();


    var cena = $('#stavkaCenovnika-cena-add').val();

    var robaIdString = $('#listaRobe-add').find(":selected").text();
    var robaId = robaIdString.substr(0, robaIdString.indexOf('.'));

    var data = {
        "cena": cena,
        "cenovnikId" : cenovnikId,
        "robaId": robaId
    };

    console.log(data);

    $.ajax({
        type: "POST",
        url: "api/stavka-cenovnika",
        data: JSON.stringify(data),
        contentType: "application/json",
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

$('#stavkeCenovnika').on( 'click', 'tr', function () {
    var stavkaCenovnikaId = $(this).children(':first').text();
    $('#edit-stavkaCenovnika').modal('toggle');

    var stavkaCenovnika = sveStavkeCenovnika.find(function(element) {
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
               "cenovnikId" : cenovnikId,
               "robaId": stavkaCenovnika.robaId
           };

           console.log(data);

        $.ajax({
            type: "PUT",
            url: "api/stavka-cenovnika/"+stavkaCenovnikaId,
            data: JSON.stringify(data),
            contentType: "application/json",
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


    $('#stavkaCenovnika-edit-form').on( 'click', '.btn-danger', function (e){
    e.preventDefault();
    e.stopImmediatePropagation();

     if (confirm('Are you sure you want do delete this Stavku Cenovnika?')) {
            $.ajax({
                type: 'DELETE',
                url: "api/stavka-cenovnika/" + stavkaCenovnikaId,
                contentType: "application/json",
                success: function (response) {
                location.reload(true); //reloads from server rather than browser cache
                },
                error: function (err) {
                    alert("Can't delete this Stavku Cenovnika");
                }
            });
        }
        });

    } );

//TODO prikaz loga firme (jos jedan td koji ce biti limitrane velicine