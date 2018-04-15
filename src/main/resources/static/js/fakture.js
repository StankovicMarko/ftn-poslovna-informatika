    var svaPreduzeca;
    var preduzeceId;
    var fakturePreduzeca;



$(document).ready(function () {

    loadPreduzeca();


});

function loadPreduzeca() {
    $.ajax({
        type: "GET",
        url: "api/preduzece",
        dataType: "json",
        success: function (preduzeca) {
                svaPreduzeca=preduzeca;
                preduzeca.forEach(function (preduzece) {
                    $('#lista-preduzeca').append('<option value="' + preduzece.id +
                    '. ' + preduzece.naziv +
                    ', PIB: ' + preduzece.pib +'"></option>');
                });
            }

    });
}

$('#pretraga-preduzeca').on('input', function() {

     var preduzeceIdString = this.value;



     preduzeceId = preduzeceIdString.substr(0, preduzeceIdString.indexOf('.'));


if(preduzeceId.match(/^-{0,1}\d+$/)){
    $('#pretraga-partnera').val("");
     $('#pretraga-godina').val("");
     loadFakture(preduzeceId);}
     else{



     }



});


function loadFakture(preduzeceIdu) {
    $.ajax({
        type: "GET",
        url: "api/faktura/"+preduzeceId,
        dataType: "json",
        success: function (fakture) {
        fakturePreduzeca = fakture;
                fakture.forEach(function (faktura) {
                    $('#fakture').append('<tr> <td style="display:none;">' + faktura.id+
                                        '</td> <td>'+faktura.brojFakture+
                                        '</td> <td>'+faktura.datumFakture+
                                        '</td> <td>'+faktura.datumValute+
                                        '</td> <td>'+faktura.osnovica+
                                        '</td> <td>'+faktura.ukupanPdv+
                                        '</td> <td>'+faktura.iznosZaPlacanje+
                                        '</td> <td>'+faktura.status+
                                        '</td> <td>'+faktura.poslovniPartnerNaziv+
                                        '</td> <td>'+faktura.poslovnaGodinaBroj+
                                        '</td> </tr>');
                });
            }

    });
}

$('#btn-add-faktura').click(function (e) {
    if (preduzeceId){
        $('#add-faktura').modal('toggle');

        $.ajax({
                      type: "GET",
                      url: "api/poslovni-partner/preduzece/"+preduzeceId,
                      dataType: "json",
                      success: function (poslovniPartneri) {
                              $('#lista-partnera').empty();


                               poslovniPartneri.forEach(function (partner) {
                                        $('#lista-partnera').append('<option value="' + partner.id +
                                                                    '. ' + partner.naziv +
                                                                    ', Adresa: ' + partner.adresa +
                                                                    ', Vrsta: ' + partner.vrsta +'"></option>');
                                                });
                                            }});


        $.ajax({
                   type: "GET",
                   url: "api/poslovna-godina/1",
                   dataType: "json",
                   success: function (poslovnaGodina) {
                           $('#lista-godina').empty();
                           $('#lista-godina').append('<option my-id="'+poslovnaGodina.id+'" value="' + poslovnaGodina.godina+ '"></option>');



                       }

               });






    }else{
        alert("Choose Preduzece");} });


$('#faktura-add-form').submit(function (e) {
    e.preventDefault();

    var partnerString= $('#pretraga-partnera').val();
    poslovniPartnerId = partnerString.substr(0, partnerString.indexOf('.'));

    var poslovnaGodinaId = $('#pretraga-godina').val();


if(poslovniPartnerId.match(/^-{0,1}\d+$/) && poslovnaGodinaId.match(/^-{0,1}\d+$/)){

     var data = {
         "preduzeceId" : preduzeceId,
         "poslovniPartnerId": poslovniPartnerId,
         "status": "st",
         "poslovnaGodinaId": 1
     };

     console.log(data);

     $.ajax({
         type: "POST",
         url: "api/faktura",
         data: JSON.stringify(data),
         contentType: "application/json",
         success: function (response) {
         console.log(response);
             $('#add-faktura').modal('toggle');
             location.reload(true); //reloads from server rather than browser cache
 //            alert(response['message']);
         },
         error: function (err) {
             var json = err.responseJSON;
             alert(json['message']);
         }
     });



     }
     else{}




});

$('#fakture').on( 'click', 'tr', function () {
    var fakturaId = $(this).children(':first').text();
    $('#edit-faktura').modal('toggle');

    var faktura = fakturePreduzeca.find(function(element) {
                           return element.id == fakturaId;
                         });

console.log(faktura);

    var id = $('#fakt-id').html(faktura.id);
    var broj = $('#fakt-br').html(faktura.brojFakture);
    var datum = $('#fakt-datum').html(faktura.datumFakture);
    var valuta = $('#fakt-valute').html(faktura.datumValute);
    var osnovica = $('#fakt-osnovica').html(faktura.osnovica);
    var pdv = $('#fakt-pdv').html(faktura.ukupanPdv);
    var iznos = $('#fakt-iznos').html(faktura.iznosZaPlacanje);
    var status = $('#fakt-status').val(faktura.status);
    var partner = $('#fakt-partner').html(faktura.poslovniPartnerNaziv);
    var godina = $('#fakt-godina').html(faktura.poslovnaGodinaBroj);


       $.ajax({
          type: "GET",
          url: "api/poslovna-godina/1",
          dataType: "json",
          success: function (poslovnaGodina) {
                  $('#lista-godina').empty();
                  $('#lista-godina').append('<option my-id="'+poslovnaGodina.id+'" value="' + poslovnaGodina.godina+ '"></option>');



              }

                  });




//    $("#preduzeca").empty();
//
//     $.ajax({
//      type: "GET",
//      url: "api/preduzece/mesto/"+mestoId,
//      dataType: "json",
//      success: function (preduzeca) {
//             preduzeca.forEach(function (pred) {
//                                     $('#preduzeca').append('<tr> <td>'+pred.naziv+'</td> <td>'+pred.adresa+'</td> <td> ' +pred.pib + '</td> </tr>');
//                                 });
//          }});
//
//
//
//
//
//    // mogucnost submita menjanja podatak i delete brisanja
//
//    $('#mesto-edit-form').submit(function (e) {
//        e.preventDefault();
//
//
//        var data = {
//            "grad": grad.val(),
//            "drzava": drzava.val()
//        };
//
//        $.ajax({
//            type: "PUT",
//            url: "api/mesto/"+mestoId,
//            data: JSON.stringify(data),
//            contentType: "application/json",
//            success: function (response) {
//            console.log(response);
//                $('#edit-mesto').modal('toggle');
//                location.reload(true); //reloads from server rather than browser cache
//    //            alert(response['message']);
//            },
//            error: function (err) {
//                var json = err.responseJSON;
//                alert(json['message']);
//            }
//        });
//
//    });


    $('#edit-faktura').on( 'click', '.btn-danger', function (e){
    e.preventDefault();

     if (confirm('Are you sure you want do delete this Faktura?')) {
            $.ajax({
                type: 'DELETE',
                url: 'api/faktura/' + fakturaId,
                contentType: "application/json",
                success: function (response) {
                location.reload(true); //reloads from server rather than browser cache
                },
                error: function (err) {
                    alert("Can't delete this Faktura");
                }
            });
        }
        });

    } );

//TODO prikaz loga firme (jos jedan td koji ce biti limitrane velicine