    var svaMesta;
    var svaPreduzeca;


$(document).ready(function () {

    loadPreduzeca();

    loadMesta();

});

function loadPreduzeca() {
    $.ajax({
        type: "GET",
        url: "api/preduzece",
        dataType: "json",
        success: function (preduzeca) {
                svaPreduzeca=preduzeca;
                preduzeca.forEach(function (preduzece) {
                    $('#lista-preduzeca').append('<option value="' + preduzece.id + '. ' + preduzece.pib + ', ' + preduzece.naziv +'"></option>');
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
                svaMesta=mesta;
                mesta.forEach(function (mesto) {
                    $('#mesta').append('<tr> <td style="display:none;">' + mesto.id+ '</td> <td>'+mesto.grad+'</td> <td>'+mesto.drzava+'</td> </tr>');
                });
            }

    });
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

$('#mesta').on( 'click', 'tr', function () {
    var mestoId = $(this).children(':first').text();
    $('#edit-mesto').modal('toggle');

    var mesto = svaMesta.find(function(element) {
                           return element.id == mestoId;
                         });


    var grad = $('#mesto-grad-edit').val(mesto.grad);
    var drzava = $('#mesto-drzava-edit').val(mesto.drzava);
    $("#preduzeca").empty();

     $.ajax({
      type: "GET",
      url: "api/preduzece/mesto/"+mestoId,
      dataType: "json",
      success: function (preduzeca) {
             preduzeca.forEach(function (pred) {
                                     $('#preduzeca').append('<tr> <td>'+pred.naziv+'</td> <td>'+pred.adresa+'</td> <td> ' +pred.pib + '</td> </tr>');
                                 });
          }});





    // mogucnost submita menjanja podatak i delete brisanja

    $('#mesto-edit-form').submit(function (e) {
        e.preventDefault();


        var data = {
            "grad": grad.val(),
            "drzava": drzava.val()
        };

        $.ajax({
            type: "PUT",
            url: "api/mesto/"+mestoId,
            data: JSON.stringify(data),
            contentType: "application/json",
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


    $('#mesto-edit-form').on( 'click', '.btn-danger', function (e){
    e.preventDefault();

     if (confirm('Are you sure you want do delete this Mesto?')) {
            $.ajax({
                type: 'DELETE',
                url: 'api/mesto/' + mestoId,
                contentType: "application/json",
                success: function (response) {
                location.reload(true); //reloads from server rather than browser cache
                },
                error: function (err) {
                    alert("Can't delete Mesto that have Preduzeca");
                }
            });
        }
        });

    } );

//TODO prikaz loga firme (jos jedan td koji ce biti limitrane velicine