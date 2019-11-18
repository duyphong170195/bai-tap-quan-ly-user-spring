$(document).ready(function () {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/listGroupRest",
        success: function (data) {
            jQuery.each(data, function (index, value) {
                var opt = $("<option>").val(value.group_id).text(value.group_name);
                $('#group_id').append(opt);
            });
        },
        error: function (e) {
            alert(e);
        }
    });

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/listUserRest",
        data: {
            full_name: "",
            group_id: 0
        },
        dataType: 'json',
    }).then(function (data) {
        jQuery.each(data, function (index, value) {
            var $tr = $('<tr>').append(
                $('<td>').text(value.user_id),
                $('<td>').text(value.full_name),
                $('<td>').text(value.birthday),
                $('<td>').text(value.group_name),
                $('<td>').text(value.email),
                $('<td>').text(value.telephone),
                $('<td>').text(value.name_level),
                $('<td>').text(value.end_date),
                $('<td>').text(value.total)
            );
            $('#tbl_list_id').append($tr);
        });
    });

    $('#search_form').submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        $("#btn_search").prop("disabled", false);

        var full_name = $('#full_name').val();
        var group_id = $('#group_id').val();

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/listUserRest",
            data: {
                full_name: full_name,
                group_id: group_id
            },
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                var $tr = "";
                jQuery.each(data, function (index, value) {
                    $tr = $tr + "<tr>"
                        + "<td>" + value.user_id + "</td>"
                        + "<td>" + value.full_name + "</td>"
                        + "<td>" + value.birthday + "</td>"
                        + "<td>" + value.group_name + "</td>"
                        + "<td>" + value.email + "</td>"
                        + "<td>" + value.telephone + "</td>"
                        + "<td>" + value.name_level + "</td>"
                        + "<td>" + value.end_date + "</td>"
                        + "<td>" + value.total + "</td>"
                        + "</tr>";
                });
                $('#tbl_list_id').html($tr);
                $("#btn-login").prop("disabled", false);
            },
            error: function(e){
                alert(e);
            }
        });
    });
});

