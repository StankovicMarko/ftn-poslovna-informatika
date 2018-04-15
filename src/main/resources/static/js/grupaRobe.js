
    var sveGrupeRobe;
    var sveStopePDVa;
    var preduzeceId;
    var pdvId;



$(document).ready(function () {
     loadPreduzeca();
      //loadStopePDVa();

});


function loadPreduzeca() {
    $.ajax({
        type: "GET",
        url: "api/preduzece",
        dataType: "json",
        success: function (preduzeca) {
               // svaPreduzeca=preduzeca;
                preduzeca.forEach(function (preduzece) {
                    $('#lista-preduzeca').append('<option>'+preduzece.id+'. '+preduzece.naziv+'</option>');
                });
            }
    });
}
function loadStopePDVa() {
    $.ajax({
        type: "GET",
        url: "api/stopa-pdv",
        dataType: "json",
        success: function (stopePdv) {
                sveStopePDVa=stopePdv;
                stopePdv.forEach(function (stope) {
                    $('#lista-pdv').append('<option>'+ stope.pdvId +'. '+stope.procenat+'</option>');
                });
            }
    });
}

function loadGrupeRobe(pdvId) {
    $.ajax({
        type: "GET",
        url: "api/grupa-robe/"+pdvId,
        dataType: "json",
        success: function (grupaRobe) {
                sveGrupeRobe=grupaRobe;
                grupaRobe.forEach(function (grupa) {
                    $('#grupe-robe').append('<tr> <td style="display:none;">' + grupa.id+ '</td><td>' + grupa.naziv+ '</td> </tr>');
                });
            }
    });
}

$('#lista-preduzeca').on('change', function() {

 $('#lista-pdv').empty();
 $('#lista-pdv').append('<option value="" selected disabled hidden>Izaberi PDV</option>');

     var preduzeceIdString = $(this).find(":selected").text();
     preduzeceId = preduzeceIdString.substr(0, preduzeceIdString.indexOf('.'));
      loadStopePDVa();
});


$('#lista-pdv').on('change', function() {
    //$('#lista-pdv').find("option:gt(0)").remove();
    $('#grupe-robe').empty();
     var pdvIdString = $(this).find(":selected").text();
     pdvId = pdvIdString.substr(0, pdvIdString.indexOf('.'));
     loadGrupeRobe(pdvId);
});

$('#grupa-robe-add-form').submit(function (e) {
    e.preventDefault();

    var nazivRobe = $('#grupa-robe-naziv-add').val();

    var data = {
        "naziv": nazivRobe,
        "preduzeceId": preduzeceId,
        "pdvId": pdvId
    };


    $.ajax({
        type: "POST",
        url: "api/grupa-robe",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (response) {
        console.log(response);
            $('#add-grupa-robe').modal('toggle');
            location.reload(true); //reloads from server rather than browser cache
//            alert(response['message']);
        },
        error: function (err) {
            var json = err.responseJSON;
            alert(json['message']);
        }
    });

});

//DOVDE STIGLA, EDIT
$('#grupe-robe').on( 'click', 'tr', function () {
    var grupaRobeId = $(this).children(':first').text();
    $('#edit-grupa-robe').modal('toggle');

    var grupaRobe= sveGrupeRobe.find(function(element) {
            return element.id == grupaRobeId;
  });
    //console.log(sveGrupeRobe);

    var nazivRobe = $('#grupa-robe-naziv-edit').val(grupaRobe.naziv);

    $("#roba").empty();

$.ajax({
      type: "GET",
      url: "api/roba/"+grupaRobeId,
      dataType: "json",
      success: function (roba) {
             roba.forEach(function (robe) {
                                     $('#roba').append('<tr> <td>'+robe.id+'</td> <td>'+robe.naziv+'</td>  </tr>');
                                 });
          }});
    // mogucnost submita menjanja podatak i delete brisanja

    $('#grupa-robe-edit-form').submit(function (e) {
        e.preventDefault();


        var data = {
            "naziv": nazivRobe.val(),
            "preduzeceId": preduzeceId,
            "pdvId": pdvId

        };

        $.ajax({
            type: "PUT",
            url: "api/grupa-robe/"+grupaRobeId,
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (response) {
            console.log(response);
                $('#edit-grupa-robe').modal('toggle');
                location.reload(true); //reloads from server rather than browser cache
    //            alert(response['message']);
            },
            error: function (err) {
                var json = err.responseJSON;
                alert(json['message']);
            }
        });

    });


    $('#grupa-robe-edit-form').on( 'click', '.btn-danger', function (e){
    e.preventDefault();

     if (confirm('Are you sure you want do delete this Grupa Robe?')) {
            $.ajax({
                type: 'DELETE',
                url: "api/grupa-robe/"+grupaRobeId,
                contentType: "application/json",
                success: function (response) {
                location.reload(true); //reloads from server rather than browser cache
                },
                error: function (err) {
                    alert("Can't delete Grupa Robe that have Robu");
                }
            });
        }
        });

    } );

//TODO prikaz loga firme (jos jedan td koji ce biti limitrane velicine