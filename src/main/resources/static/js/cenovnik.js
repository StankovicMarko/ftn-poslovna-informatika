
    var preduzeceId;
    var sviCenovnici;
    var svaPreduzeca;


$(document).ready(function () {
     loadPreduzeca();



});



function loadCenovnici(preduzeceId) {
    $.ajax({
        type: "GET",
        url: "api/cenovnik/"+preduzeceId,
        dataType: "json",
        success: function (cenovnik) {
                sviCenovnici=cenovnik;
                cenovnik.forEach(function (cenovnik) {
                    $('#cenovnici').append('<tr> <td>' + cenovnik.id+ '</td> <td>'+cenovnik.datumVazenja+'</td> </tr>');
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
                svaPreduzeca=preduzeca;
                preduzeca.forEach(function (preduzece) {
                    $('#lista-preduzeca').append('<option>'+preduzece.id+'. '+preduzece.naziv+'</option>');
                });
            }

    });
}

$('#lista-preduzeca').on('change', function() {
    $('#cenovnici').empty();

     var preduzeceIdString = $(this).find(":selected").text();
     preduzeceId = preduzeceIdString.substr(0, preduzeceIdString.indexOf('.'));
      loadCenovnici(preduzeceId);


});

$('#cenovnik-add-form').submit(function (e) {
    e.preventDefault();


    var datumVazenja = $('#cenovnik-datumVazenja-add').val();

    var data = {
        "datumVazenja": datumVazenja,
        "preduzeceId" : preduzeceId
    };

    console.log(data);

    $.ajax({
        type: "POST",
        url: "api/cenovnik",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (response) {
        console.log(response);
            $('#add-cenovnik').modal('toggle');
            location.reload(true); //reloads from server rather than browser cache
//            alert(response['message']);
        },
        error: function (err) {
            var json = err.responseJSON;
            alert(json['message']);
        }
    });

});

$('#cenovnik').on( 'click', 'tr', function () {
    var cenovnikId = $(this).children(':first').text();
    $('#edit-cenovnik').modal('toggle');

    var cenovnik = sviCenovnici.find(function(element) {
                           return element.id == cenovnikId;
                         });


    var datumVazenja = $('#cenovnik-datumVazenja-edit').val(cenovnik.datumVazenja);
    $("#stavkeCenovnika").empty();

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