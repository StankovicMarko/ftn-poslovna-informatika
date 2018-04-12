
$(document).ready(function () {

    loadMesta();

});

function loadMesta() {
    $.ajax({
        type: "GET",
        url: "api/mesto",
        dataType: "json",
        success: function (mesta) {
                mesta.forEach(function (mesto) {
                    $('#mesta').append('<tr> <td style="display:none;">' + mesto.id+ '</td> <td>'+mesto.grad+'</td> <td>'+mesto.drzava+'</td> </tr>');
                });
            }

    });
}



$('#mesto-add-form').submit(function (e) {
    e.preventDefault();

    var grad = $('#mesto-grad-add').val();
    var drzava = $('#mesto-grad-add').val();

    var data = {
        "grad": grad,
        "drzava": drzava
    };

    $.ajax({
        type: $(this).attr("method"),
        url: $(this).attr("action"),
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

    // open edit/delete modal window with sent mesto id
    // get mesto details + preduzeca i posl partneri koji su u tom mesto (mozda bi trebalo samo preduzeca da budu)
    // mogucnost submita menjanja podatak i delete brisanja
       console.log(mestoId);


    } );
