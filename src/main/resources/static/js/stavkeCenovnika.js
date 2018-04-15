
    var sveStavkeCenovnika;
    var sviCenovnici;
    var svaPreduzeca;
    var preduzeceId;
    var cenovnikId;



$(document).ready(function () {
     loadPreduzeca();
//     loadStavkeCenovnika();

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
                    $('#stavkeCenovnika').append('<tr> <td>' + stavkde.nazivRobe+ '</td> <td>'+stavke.cena+'</td> </tr>');
                });
            }
    });
}

$('#lista-preduzeca').on('change', function() {
    $('#lista-cenovnika').empty();
//    $('#lista-cenovnika').not(':first').empty();

     var preduzeceIdString = $(this).find(":selected").text();
     preduzeceId = preduzeceIdString.substr(0, preduzeceIdString.indexOf('.'));
      loadCenovnici(preduzeceId);
});

$('#lista-cenovnika').on('change', function() {
    //$('#lista-cenovnika').find("option:gt(0)").remove();

     var cenovnikIdString = $(this).find(":selected").text();
     cenovnikId = cenovnikIdString.substr(0, cenovnikIdString.indexOf('.'));
      loadStavkeCenovnika(cenovnikId);
});

$('#stavkaCenovnika-add-form').submit(function (e) {
    e.preventDefault();


    var cena = $('#stavkaCenovnika-cena-add').val();

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


    var cena = $('#stavkaCenovnika-cena-edit').val(cenovnik.cena);
//    $("#stavkeCenovnika").empty();

     $.ajax({
      type: "GET",
      url: "api/stavka-cenovnika/cenovnik/"+cenovnikId,
      dataType: "json",
      success: function (cenovnika) {
             cenovnika.forEach(function (cenovnik) {
                                     $('#stavkeCenovnika').append('<tr> <td>'+cenovnik.nazivRobe+'</td> <td>'+cenovnik.cena+'</td> </tr>');
                                 });
          }});


    // mogucnost submita menjanja podatak i delete brisanja

    $('#cenovnik-edit-form').submit(function (e) {
        e.preventDefault();


        var data = {
            "datumVazenja": datumVazenja.val(),
            "preduzeceId": preduzeceId
        };

        $.ajax({
            type: "PUT",
            url: "api/cenovnik/"+cenovnikId,
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


    $('#cenovnik-edit-form').on( 'click', '.btn-danger', function (e){
    e.preventDefault();

     if (confirm('Are you sure you want do delete this Cenovnik?')) {
            $.ajax({
                type: 'DELETE',
                url: 'api/cenovnik/' + cenovnikId,
                contentType: "application/json",
                success: function (response) {
                location.reload(true); //reloads from server rather than browser cache
                },
                error: function (err) {
                    alert("Can't delete Cenovnik that have Stavke Cenovnika");
                }
            });
        }
        });

    } );

//TODO prikaz loga firme (jos jedan td koji ce biti limitrane velicine