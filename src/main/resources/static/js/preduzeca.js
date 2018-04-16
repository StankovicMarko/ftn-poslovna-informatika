    var svaPreduzeca;
    var svaMesta;

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
                    $('#preduzeca').append('<tr> <td style="display:none;">' + preduzece.id+ '</td> <td>'+preduzece.naziv+'</td> <td>'+preduzece.adresa+'</td> <td>'+preduzece.pib+'</td> <td>'+preduzece.telefon+'</td> <td>'+preduzece.email+'</td> <td>'+preduzece.logoPath+'</td> </tr>');
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
                    $('#lista-mesta').append('<option>'+mesto.id+'. '+mesto.drzava+', '+mesto.grad+'</option>');
                });
            }

    });
}

$('#preduzeca-add-form').submit(function (e) {
    e.preventDefault();

    var naziv = $('#preduzeca-naziv-add').val();
    var adresa = $('#preduzeca-adresa-add').val();
    var pib = $('#preduzeca-pib-add').val();
    var telefon = $('#preduzeca-telefon-add').val();
    var email = $('#preduzeca-email-add').val();
    var logo = $('#preduzeca-logo-add').val();
    var mestoIdString = $('#lista-mesta').find(":selected").text();

    var mestoId = mestoIdString.substr(0, mestoIdString.indexOf('.'));


    var data = {
        "naziv": naziv,
        "adresa": adresa,
        "pib": pib,
        "telefon": telefon,
        "email": email,
        "logoPath": logo,
        "mestoId": mestoId

    };



    $.ajax({
        type: "POST",
        url: "api/preduzece",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (response) {
        console.log(response);
            $('#add-preduzeca').modal('toggle');
            location.reload(true); //reloads from server rather than browser cache
//            alert(response['message']);
        },
        error: function (err) {
            var json = err.responseJSON;
            alert(json['message']);
        }
    });

});

$('#preduzeca').on( 'click', 'tr', function () {
    var preduzeceId = $(this).children(':first').text();
    $('#edit-preduzeca').modal('toggle');

    var preduzece = svaPreduzeca.find(function(element) {
                           return element.id == preduzeceId;
                         });


    var naziv = $('#preduzeca-naziv-edit').val(preduzece.naziv);
    var adresa = $('#preduzeca-adresa-edit').val(preduzece.adresa);
    var pib = $('#preduzeca-pib-edit').val(preduzece.pib);
    var telefon = $('#preduzeca-telefon-edit').val(preduzece.telefon);
    var email = $('#preduzeca-email-edit').val(preduzece.email);
    var logo = $('#preduzeca-logo-edit').val(preduzece.logoPath);

    $("#cenovnik").empty();
    $("#faktura").empty();
    $("#grupaRobe").empty();
    $("#poslovniPartner").empty();

     $.ajax({
      type: "GET",
      url: "api/cenovnik/"+preduzeceId,
      dataType: "json",
      success: function (cenovnici) {
             cenovnici.forEach(function (cenovnik) {
                                     $('#cenovnik').append('<tr> <td>'+cenovnik.id+'</td> <td>'+cenovnik.datumVazenja+'</td>  </tr>');
                                 });
          }});
      $.ajax({
            type: "GET",
            url: "api/faktura/"+preduzeceId,
            dataType: "json",
            success: function (faktura) {
                   faktura.forEach(function (faktura) {
                                           $('#faktura').append('<tr> <td>'+faktura.brojFakture+
                                           '</td><td>'+faktura.datumFakture+
                                           '</td> <td>'+faktura.datumValute+
                                           '</td><td>'+faktura.osnovica+
                                           '</td> <td>'+faktura.ukupanPDV+
                                           '</td> <td>'+faktura.iznosZaPlacanje+
                                           '</td> <td>'+faktura.status+'</td> </tr>');
                                       });
                }});
      $.ajax({
          type: "GET",
          url: "api/grupa-robe/"+preduzeceId,
          dataType: "json",
          success: function (grupeRobe) {
                 grupeRobe.forEach(function (grupaRobe) {
                                         $('#grupaRobe').append('<tr> <td>'+grupaRobe.id+'</td> <td>'+grupaRobe.naziv+'</td>  </tr>');
                                     });
              }});

      $.ajax({
          type: "GET",
          url: "api/poslovni-partner/"+preduzeceId,
          dataType: "json",
          success: function (poslovniPartneri) {
                 poslovniPartneri.forEach(function (poslovniPartner) {
                                         $('#poslovniPartner').append('<tr> <td>'+poslovniPartner.naziv+
                                         '</td> <td>'+poslovniPartner.adresa+
                                         '</td> <td>'+poslovniPartner.vrsta+'</td>  </tr>');
                                     });
              }});






    // mogucnost submita menjanja podatak i delete brisanja

    $('#preduzeca-edit-form').submit(function (e) {
        e.preventDefault();


        var data = {
             "naziv": naziv.val(),
             "adresa": adresa.val(),
             "pib": pib.val(),
             "telefon": telefon.val(),
             "email": email.val(),
             "logoPath": logo.val(),
             "mestoId": preduzece.mestoId
        };

        $.ajax({
            type: "PUT",
            url: "api/preduzece/"+preduzeceId,
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (response) {
            console.log(response);
                $('#edit-preduzeca').modal('toggle');
                location.reload(true); //reloads from server rather than browser cache
    //            alert(response['message']);
            },
            error: function (err) {
                var json = err.responseJSON;
                alert(json['message']);
            }
        });

    });


    $('#preduzeca-edit-form').on( 'click', '.btn-danger', function (e){
    e.preventDefault();
    if (confirm('Are you sure you want do delete this Preduzece?')) {
            $.ajax({
                type: 'DELETE',
                url: 'api/preduzece/' + preduzeceId,
                contentType: "application/json",
                success: function (response) {
                location.reload(true); //reloads from server rather than browser cache
                },
                error: function (err) {
                    alert("Can't delete Preduzeće that have Fakturu");
                }
            });
        }

        });

    } );

//TODO prikaz loga firme (jos jedan td koji ce biti limitrane velicine