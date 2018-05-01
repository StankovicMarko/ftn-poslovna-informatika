var sviCenovnici;
var token;
var preduzeceId;
var page_number = 0;

$(document).ready(function () {

    token = localStorage.getItem('token');
    preduzeceId = localStorage.getItem("preduzeceId");

    if (!token) {
        window.location.replace("/index.html");
    }

    loadCenovnici(page_number);
});


function loadCenovnici(page) {
    $("#cenovnici").empty();
    $("#page_number").text(page);

    var url = "api/cenovnik";
    if (preduzeceId != 1) {
        url = "api/cenovnik/" + preduzeceId;
    }

    $.ajax({
        type: "GET",
        url: url + "?size=3&page=" + page,
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (cenovnik) {
            sviCenovnici = cenovnik;
            cenovnik.forEach(function (cenovnik) {
                $('#cenovnici').append('<tr> <td>' + cenovnik.id + '</td> <td>' + cenovnik.datumVazenja + '</td> </tr>');
            });
        }
    });
}

function onLeftArrowClick() {
    if (page_number > 0) {
        page_number -= 1;
        loadCenovnici(page_number);
    }
}

function onRightArrowClick() {
    page_number += 1;
    loadCenovnici(page_number);
}

$('#cenovnik-add-form').submit(function (e) {
    e.preventDefault();

    var datumVazenja = $('#cenovnik-datumVazenja-add').val();

    var data = {
        "datumVazenja": datumVazenja,
        "preduzeceId": preduzeceId
    };

    console.log(data);

    $.ajax({
        type: "POST",
        url: "api/cenovnik",
        data: JSON.stringify(data),
        contentType: "application/json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
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

$('#cenovnici').on('click', 'tr', function () {
    var cenovnikId = $(this).children(':first').text();
    $('#edit-cenovnik').modal('toggle');

    var cenovnik = sviCenovnici.find(function (element) {
        return element.id == cenovnikId;
    });


    var datumVazenja = $('#cenovnik-datumVazenja-edit').val(cenovnik.datumVazenja);
    $("#stavkeCenovnika").empty();

    $.ajax({
        type: "GET",
        url: "api/stavka-cenovnika/cenovnik/" + cenovnikId,
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (cenovnika) {
            cenovnika.forEach(function (cenovnik) {
                $('#stavkeCenovnika').append('<tr> <td>' + cenovnik.nazivRobe + '</td> <td>' + cenovnik.cena + '</td> </tr>');
            });
        }
    });


    // mogucnost submita menjanja podatak i delete brisanja

    $('#cenovnik-edit-form').submit(function (e) {
        e.preventDefault();

        var data = {
            "datumVazenja": datumVazenja.val(),
            "preduzeceId": preduzeceId
        };

        $.ajax({
            type: "PUT",
            url: "api/cenovnik/" + cenovnikId,
            data: JSON.stringify(data),
            contentType: "application/json",
            beforeSend: function (request) {
                request.setRequestHeader("Authorization", token);
            },
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


    $('#cenovnik-edit-form').on('click', '.btn-danger', function (e) {
        e.preventDefault();

        if (confirm('Are you sure you want do delete this Cenovnik?')) {
            $.ajax({
                type: 'DELETE',
                url: 'api/cenovnik/' + cenovnikId,
                contentType: "application/json",
                beforeSend: function (request) {
                    request.setRequestHeader("Authorization", token);
                },
                success: function (response) {
                    location.reload(true); //reloads from server rather than browser cache
                },
                error: function (err) {
                    alert("Can't delete Cenovnik that have Stavke Cenovnika");
                }
            });
        }
    });

});

//TODO prikaz loga firme (jos jedan td koji ce biti limitrane velicine