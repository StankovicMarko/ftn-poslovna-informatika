var sveJedinice;
var token;

$(document).ready(function () {

    token = localStorage.getItem('token');
    // preduzeceId = localStorage.getItem("preduzeceId");

    if (!token) {
        window.location.replace("/index.html");
    }

    loadJedinice();

});

function loadJedinice() {
    $.ajax({
        type: "GET",
        url: "api/jedinica-mere",
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (jedinicaMere) {
            sveJedinice = jedinicaMere;
            jedinicaMere.forEach(function (jedinicaMere) {
                $('#jedinicaMere').append('<tr> <td>' + jedinicaMere.id + '</td> <td>' + jedinicaMere.naziv + '</td></tr>');
            });
        }

    });
}

$('#jedinicaMere-add-form').submit(function (e) {
    e.preventDefault();

    var naziv = $('#jedinicaMere-naziv-add').val();


    var data = {
        "naziv": naziv
    };

    $.ajax({
        type: "POST",
        url: "api/jedinica-mere/",
        data: JSON.stringify(data),
        contentType: "application/json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (response) {
            console.log(response);
            $('#add-jedinicaMere').modal('toggle');
            location.reload(true); //reloads from server rather than browser cache
//            alert(response['message']);
        },
        error: function (err) {
            var json = err.responseJSON;
            alert(json['message']);
        }
    });

});

$('#jedinicaMere').on('click', 'tr', function () {
    var jedinicaMereId = $(this).children(':first').text();
    $('#edit-jedinicaMere').modal('toggle');

    var jedinicaMere = sveJedinice.find(function (element) {
        return element.id == jedinicaMereId;
    });


    var naziv = $('#jedinicaMere-naziv-edit').val(jedinicaMere.naziv);
    $("#roba").empty();

    $.ajax({
        type: "GET",
        url: "api/roba/jedinica-mere/" + jedinicaMereId,
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (roba) {
            roba.forEach(function (roba) {
                $('#roba').append('<tr> <td>' + roba.naziv + '</td> </tr>');
            });
        }
    });


    // mogucnost submita menjanja podatak i delete brisanja

    $('#jedinicaMere-edit-form').submit(function (e) {
        e.preventDefault();


        var data = {
            "naziv": naziv.val()
        };

        $.ajax({
            type: "PUT",
            url: "api/jedinica-mere/" + jedinicaMereId,
            data: JSON.stringify(data),
            contentType: "application/json",
            beforeSend: function (request) {
                request.setRequestHeader("Authorization", token);
            },
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


    $('#jedinicaMere-edit-form').on('click', '.btn-danger', function (e) {
        e.preventDefault();

        if (confirm('Are you sure you want do delete this Jedinicu Mere?')) {
            $.ajax({
                type: 'DELETE',
                url: 'api/jedinica-mere/' + jedinicaMereId,
                contentType: "application/json",
                beforeSend: function (request) {
                    request.setRequestHeader("Authorization", token);
                },
                success: function (response) {
                    location.reload(true); //reloads from server rather than browser cache
                },
                error: function (err) {
                    alert("Can't delete Jedinicu Mere that have Robu");
                }
            });
        }
    });

});

//TODO prikaz loga firme (jos jedan td koji ce biti limitrane velicine