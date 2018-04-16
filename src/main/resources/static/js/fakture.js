    var svaPreduzeca;
    var preduzeceId;
    var fakturePreduzeca;
    var ucitaneStavke;
    var stavkaId;
    var cenovnikId;
    var dodateStavkeFakture=[];



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
    dodateStavkeFakture=[];
    var fakturaId = $(this).children(':first').text();
    $('#edit-faktura').modal('toggle');

    var faktura = fakturePreduzeca.find(function(element) {
                           return element.id == fakturaId;
                         });

//console.log(faktura);

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

    $('#pretraga-cenovnika').val('');
    $('#pretraga-stavki').val('');
    $('#lista-cenovnika').empty();
    $('#lista-stavki').empty();
    $('#cena').text('');

       $.ajax({
          type: "GET",
          url: "api/cenovnik/"+preduzeceId,
          dataType: "json",
          success: function (cenovnici) {
                 cenovnici.forEach(function (cenovnik) {
                     $('#lista-cenovnika').append('<option value="' + cenovnik.id +
                                                 '. ' + cenovnik.datumVazenja +'"></option>');
                             });}});

        $('#pretraga-cenovnika').on('input', function(e) {
            e.stopImmediatePropagation();

            $('#lista-stavki').empty();
            $('#cena').text('');
            $('#pretraga-stavki').val('');


             var cenovnikString = this.value;
             cenovnikId = cenovnikString.substr(0, cenovnikString.indexOf('.'));


        if(cenovnikId.match(/^-{0,1}\d+$/)){

            $.ajax({
                      type: "GET",
                      url: "api/stavka-cenovnika/cenovnik/"+cenovnikId,
                      dataType: "json",
                      success: function (stavke) {
                              ucitaneStavke=stavke;
                             stavke.forEach(function (stavka) {
                                 $('#lista-stavki').append('<option value="' + stavka.id +
                                                             '. ' + stavka.nazivRobe+'"></option>');
                                         });}});
             //$('#pretraga-stavki').val('');

             }else{}});


        $('#pretraga-stavki').on('input', function() {

             var stavkaString = this.value;
             stavkaId = stavkaString.substr(0, stavkaString.indexOf('.'));


        if(stavkaId.match(/^-{0,1}\d+$/)){

            var stavka = ucitaneStavke.find(function(element) {
                                       return element.id == stavkaId;
                                     });
            $("#cena").html(stavka.cena)
             }else{}});

        $('.btn-secondary').on( 'click', function (e) {

            e.stopImmediatePropagation();

            var kol = parseInt($("#kolicina").val());
            var r = parseInt($("#rabat").val());
             var c = parseInt($("#cena").text());

            var osn = kol * c * (100 - r) / 100;
            $("#osnovica").html(osn);

             var stopaPdvStr = $("#pretraga-pdv").val();
             stopaId = stopaPdvStr.substr(0, stopaPdvStr.indexOf('.'));

            var procenat;
            if(stopaId == 1){
            procenat=10;}else{
            procenat=20;}

            var izn = osn * procenat / 100
            $("#iznos-pdv").html(izn);

            var iznStav = osn+izn;
            $("#iznos-stavke").html(iznStav);

        });

        $('.btn-primary').on( 'click', function (e) {

                    e.stopImmediatePropagation();

                    var kol = parseInt($("#kolicina").val());
                    var r = parseInt($("#rabat").val());
                     var c = parseInt($("#cena").text());

                    var osn = kol * c * (100 - r) / 100;

                     var stopaPdvStr = $("#pretraga-pdv").val();
                     stopaId = stopaPdvStr.substr(0, stopaPdvStr.indexOf('.'));

                    var procenat;
                    if(stopaId == 1){
                    procenat=10;}else{
                    procenat=20;}

                    var izn = osn * procenat / 100
                    var iznStav = osn+izn;

                    var stavka = ucitaneStavke.find(function(element) {
                                                           return element.id == stavkaId;
                                                         });

                    var data = {
                    "cenovnikId" :cenovnikId,
                    "robaId": stavka.robaId,
                    "preduzeceId": preduzeceId,
                    "iznosStavke": iznStav,
                    "iznosPDV": izn,
                    "procenatPDV": procenat,
                    "osnovicaZaPDV": osn,
                    "rabat": r,
                    "jedinicnaCena": c,
                    "kolicina": kol,
                    "fakturaId": fakturaId
                    }

                dodateStavkeFakture.push(data);

                console.log(dodateStavkeFakture);

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