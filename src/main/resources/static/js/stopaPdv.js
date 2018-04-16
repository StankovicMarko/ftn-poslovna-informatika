    var sveStopePDVa;

$(document).ready(function () {

    loadStopePDVa();

});

function loadStopePDVa() {
    $.ajax({
        type: "GET",
        url: "api/stopa-pdv",
        dataType: "json",
        success: function (stopaPdv) {
                sveStopePDVa=stopaPdv;
                stopaPdv.forEach(function (stopa) {
                    $('#stope-pdv').append('<tr> <td style="display:none;">' + stopa.id+ '</td> <td>'+stopa.procenat+'</td> <td>'+stopa.datumVazenja+'</td></tr>');
                });
            }

    });
}

$('#stopa-pdv-add-form').submit(function (e) {
    e.preventDefault();

    var procenat = $('#stopa-pdv-procenat-add').val();
    var datumVazenja = $('#stopa-pdv-datum-vazenja-add').val();


    var data = {
        "procenat": procenat,
        "datumVazenja": datumVazenja
    };

    $.ajax({
        type: "POST",
        url: "api/stopa-pdv",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (response) {
        console.log(response);
            $('#add-stopa-pdv').modal('toggle');
            location.reload(true); //reloads from server rather than browser cache
//            alert(response['message']);
        },
        error: function (err) {
            var json = err.responseJSON;
            alert(json['message']);
        }
    });

});

$('#stope-pdv').on( 'click', 'tr', function () {
    var stopaPdvId = $(this).children(':first').text();
    $('#edit-stopa-pdv').modal('toggle');

    var stopaPdv = sveStopePDVa.find(function(element) {
                           return element.id == stopaPdvId;
                         });


    var procenat = $('#stopa-pdv-procenat-edit').val(stopaPdv.procenat);
    var datumVazenja = $('#stopa-pdv-datum-vazenja-edit').val(stopaPdv.datumVazenja);


    // mogucnost submita menjanja podatak i delete brisanja

    $('#jedinicaMere-edit-form').submit(function (e) {
        e.preventDefault();


        var data = {
            "naziv": naziv.val()
        };

        $.ajax({
            type: "PUT",
            url: "api/jedinica-mere/"+jedinicaMereId,
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (response) {
            console.log(response);
                $('#edit-jedinicaMere').modal('toggle');
                location.reload(true); //reloads from server rather than browser cache
    //            alert(response['message']);
            },
            error: function (err) {
                var json = err.responseJSON;
                alert(json['message']);
            }
        });

    });


    $('#jedinicaMere-edit-form').on( 'click', '.btn-danger', function (e){
    e.preventDefault();

     if (confirm('Are you sure you want do delete this Jedinicu Mere?')) {
            $.ajax({
                type: 'DELETE',
                url: 'api/jedinica-mere/' + jedinicaMereId,
                contentType: "application/json",
                success: function (response) {
                location.reload(true); //reloads from server rather than browser cache
                },
                error: function (err) {
                    alert("Can't delete Jedinicu Mere that have Robu");
                }
            });
        }
        });

    } );

//TODO prikaz loga firme (jos jedan td koji ce biti limitrane velicine