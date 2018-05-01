var sveGrupeRobe;
var sveStopePDVa;
var pdvId;
var token;
var preduzeceId;
var page_number = 0;

$(document).ready(function () {

    token = localStorage.getItem('token');
    preduzeceId = localStorage.getItem("preduzeceId");

    if (!token) {
        window.location.replace("/index.html");
    }

    loadStopePDVa();
});

function loadStopePDVa() {
    $.ajax({
        type: "GET",
        url: "api/stopa-pdv",
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (stopePdv) {
            sveStopePDVa = stopePdv;
            stopePdv.forEach(function (stope) {
                $('#lista-pdv').append('<option>' + stope.pdvId + '. ' + stope.procenat + '</option>');
            });
        }
    });
}

function loadGrupeRobe(pdvId, page) {
    $("#grupe-robe").empty();
    $("#page_number").text(page);

    var url = "api/grupa-robe";
    if (preduzeceId != 1) {
        url = "api/grupa-robe/preduzece/" + preduzeceId + "/pdv/" + pdvId;
    }

    $.ajax({
        type: "GET",
        url: url + "?size=3&page=" + page,
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (grupaRobe) {
            sveGrupeRobe = grupaRobe;
            grupaRobe.forEach(function (grupa) {
                $('#grupe-robe').append('<tr> <td style="display:none;">' + grupa.id + '</td><td>' + grupa.naziv + '</td> </tr>');
            });
        }
    });
}

function onLeftArrowClick() {
    if (page_number > 0) {
        page_number -= 1;
        loadGrupeRobe(pdvId, page_number);
    }
}

function onRightArrowClick() {
    page_number += 1;
    loadGrupeRobe(pdvId, page_number);
}

$('#lista-pdv').on('change', function (e) {
    e.stopImmediatePropagation()
    //$('#lista-pdv').find("option:gt(0)").remove();
    $('#grupe-robe').empty();
    var pdvIdString = $(this).find(":selected").text();
    pdvId = pdvIdString.substr(0, pdvIdString.indexOf('.'));
    loadGrupeRobe(pdvId, page_number);
});

$('#grupa-robe-add-form').submit(function (e) {
    e.preventDefault();

    var nazivRobe = $('#grupa-robe-naziv-add').val();

    var data = {
        "naziv": nazivRobe,
        "preduzeceId": preduzeceId,
        "pdvId": pdvId
    };


    $.ajax({
        type: "POST",
        url: "api/grupa-robe",
        data: JSON.stringify(data),
        contentType: "application/json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (response) {
            console.log(response);
            $('#add-grupa-robe').modal('toggle');
            location.reload(true); //reloads from server rather than browser cache
//            alert(response['message']);
        },
        error: function (err) {
            var json = err.responseJSON;
            alert(json['message']);
        }
    });

});


$('#grupe-robe').on('click', 'tr', function () {
    var grupaRobeId = $(this).children(':first').text();
    $('#edit-grupa-robe').modal('toggle');

    var grupaRobe = sveGrupeRobe.find(function (element) {
        return element.id == grupaRobeId;
    });


    var nazivRobe = $('#grupa-robe-naziv-edit').val(grupaRobe.naziv);

    $("#roba").empty();

    $.ajax({
        type: "GET",
        url: "api/roba/grupa-robe/" + grupaRobeId,
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function (roba) {
            roba.forEach(function (robe) {
                $('#roba').append('<tr> <td>' + robe.id + '</td> <td>' + robe.naziv + '</td>  </tr>');
            });
        }
    });


    $('#grupa-robe-edit-form').submit(function (e) {
        e.preventDefault();


        var data = {
            "naziv": nazivRobe.val(),
            "preduzeceId": preduzeceId,
            "pdvId": pdvId
        };

        console.log(data);
        console.log(grupaRobeId);


        $.ajax({
            type: "PUT",
            url: "api/grupa-robe/" + grupaRobeId,
            data: JSON.stringify(data),
            contentType: "application/json",
            beforeSend: function (request) {
                request.setRequestHeader("Authorization", token);
            },
            success: function (response) {
                console.log(response);
                $('#edit-grupa-robe').modal('toggle');
                location.reload(true); //reloads from server rather than browser cache
                //            alert(response['message']);
            },
            error: function (err) {
                var json = err.responseJSON;
                alert(json['message']);
            }
        });

    });


    $('#grupa-robe-edit-form').on('click', '.btn-danger', function (e) {
        e.preventDefault();

        if (confirm('Are you sure you want do delete this Grupa Robe?')) {
            $.ajax({
                type: 'DELETE',
                url: "api/grupa-robe/" + grupaRobeId,
                contentType: "application/json",
                beforeSend: function (request) {
                    request.setRequestHeader("Authorization", token);
                },
                success: function (response) {
                    location.reload(true); //reloads from server rather than browser cache
                },
                error: function (err) {
                    alert("Can't delete Grupa Robe that have Robu");
                }
            });
        }
    });

});

//TODO prikaz loga firme (jos jedan td koji ce biti limitrane velicine