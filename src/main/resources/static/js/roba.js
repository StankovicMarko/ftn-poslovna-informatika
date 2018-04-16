
    var svaRoba;

   // var sveGrupeRobe;
    var grupaRobeId;

    //var sveJedinice;
    //var jediniceMereId;




$(document).ready(function () {
     loadGrupeRobe();
     loadJedinice();

});


function loadGrupeRobe() {
    $.ajax({
        type: "GET",
        url: "api/grupa-robe",
        dataType: "json",
        success: function (grupaRobe) {
                //sveGrupeRobe=grupaRobe
                grupaRobe.forEach(function (grupa) {
                    $('#lista-grupe-robe').append('<option>' + grupa.id+ '. ' + grupa.naziv+ '</option>' );
                });
            }
    });
}

function loadJedinice() {
    $.ajax({
        type: "GET",
        url: "api/jedinica-mere",
        dataType: "json",
        success: function (jediniceMere) {
                //sveJedinice=jediniceMere;
                jediniceMere.forEach(function (jedinicaMere) {
                    $('#roba-jedinica-mere-add').append('<option>' + jedinicaMere.id+ '. '+jedinicaMere.naziv+'</option>');
                });
            }

    });
}

function loadRoba(grupaRobeId) {
    $.ajax({
        type: "GET",
        url: "api/roba/grupa-robe/"+grupaRobeId,
        dataType: "json",
        success: function (robe) {
               svaRoba=robe;
                robe.forEach(function (roba) {
                    $('#roba').append('<tr> <td style="display:none;">' + roba.id+'</td><td>' + roba.naziv+'</td><td>' + roba.jedinicaMereId+ '</td> </tr>');
                });
            }
    });
}


$('#lista-grupe-robe').on('change', function() {
     $('#roba').empty();

     var grupaRobeIdString = $(this).find(":selected").text();
     grupaRobeId = grupaRobeIdString.substr(0, grupaRobeIdString.indexOf('.'));
      loadRoba(grupaRobeId);
});

$('#roba-add-form').submit(function (e) {
    e.preventDefault();

    var nazivRobe = $('#roba-naziv-add').val();

    var jedinicaMereIdString = $('#roba-jedinica-mere-add').find(":selected").text();
    var jedinicaMereId = jedinicaMereIdString.substr(0, jedinicaMereIdString.indexOf('.'));
//    var jedinicaMere = $('#roba-jedinica-mere-add').val();

    var data = {
        "naziv": nazivRobe,
        "jedinicaMereId": jedinicaMereId,
        "grupaRobeId": grupaRobeId
    };

    $.ajax({
        type: "POST",
        url: "api/roba",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (response) {
        console.log(response);
            $('#add-robu').modal('toggle');
            location.reload(true); //reloads from server rather than browser cache
//            alert(response['message']);
        },
        error: function (err) {
            var json = err.responseJSON;
            alert(json['message']);
        }
    });

});


$('#roba').on( 'click', 'tr', function () {
    var robaId = $(this).children(':first').text(); //robaId
    var jedinicaMereId = $(this).find(':nth-child(3)').text();
    $('#edit-robu').modal('toggle');

    var roba= svaRoba.find(function(element) {
            return element.id == robaId; //robaId
  });
    //console.log(sveGrupeRobe);

    var nazivRobe = $('#roba-naziv-edit').val(roba.naziv);
//    var jedinicaMere = $('#roba-jedinica-mere-edit').val(roba.jedinicaMereId); //tako nesto

//    $("#cenovnik").empty();
//    $("#faktura").empty();
//
//
//    $.ajax({
//          type: "GET",
//          url: "api/cenovnik/"+robaId,
//          dataType: "json",
//          success: function (cenovnici) {
//                 cenovnici.forEach(function (cenovnik) {
//                                         $('#cenovnik').append('<tr> <td>'+cenovnik.id+'</td> <td>'+cenovnik.datumVazenja+'</td>  </tr>');
//                                     });
//       }});
//
//
//
////////////////NE RADI
//    $.ajax({
//          type: "GET",
//          url: "api/faktura/"+robaId,
//          dataType: "json",
//          success: function (faktura) {
//                   faktura.forEach(function (faktura) {
//                                           $('#faktura').append('<tr> <td>'+faktura.brojFakture+
//                                           '</td><td>'+faktura.datumFakture+
//                                           '</td> <td>'+faktura.datumValute+
//                                           '</td><td>'+faktura.osnovica+
//                                           '</td> <td>'+faktura.ukupanPDV+
//                                           '</td> <td>'+faktura.iznosZaPlacanje+
//                                           '</td> <td>'+faktura.status+'</td> </tr>');
//                                       });
//                }});

    // mogucnost submita menjanja podatak i delete brisanja

    $('#roba-edit-form').submit(function (e) {
        e.preventDefault();


        var data = {
            "naziv": nazivRobe.val(),
            "jedinicaMereId": jedinicaMereId,
            "grupaRobeId": grupaRobeId
        };

        console.log(data);

        $.ajax({
            type: "PUT",
            url: "api/roba/"+robaId,
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (response) {
            //console.log(response);
                $('#edit-robu').modal('toggle');
                location.reload(true); //reloads from server rather than browser cache
    //            alert(response['message']);
            },
            error: function (err) {
                var json = err.responseJSON;
                alert(json['message']);
            }
        });

    });


    $('#roba-edit-form').on( 'click', '.btn-danger', function (e){
    e.preventDefault();

     if (confirm('Are you sure you want do delete this Robu?')) {
            $.ajax({
                type: 'DELETE',
                url: "api/roba/"+robaId,
                contentType: "application/json",
                success: function (response) {
                location.reload(true); //reloads from server rather than browser cache
                },
                error: function (err) {
                    alert("Can't delete Robu that have Fakturu i Cenovnik");
                }
            });
        }
        });

    } );

//TODO prikaz loga firme (jos jedan td koji ce biti limitrane velicine