$(document).ready(function () {
    var action = "";
    var sortingFullNameValue = "ASC";
    var sortingLevelValue = "ASC";
    var sortingEndDateValue = "DESC";
    var fullName = "";
    var groupName = "";

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
            var error = JSON.parse(e.responseText);
            alert(error.apierror.message);
        }
    }).then(function () {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/listUserRest",
            data: {
                full_name: "",
                group_id: 0
            },
            dataType: 'json',
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
                $('#tbl_body_id').html($tr);
            }
        });
    });

    $('#btn_search').click(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        action = "search";
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
                $('#tbl_body_id').html($tr);
                $("#btn-login").prop("disabled", false);
            },
            error: function (e) {
                alert(e.message);
            }
        });
    });

    $("#sortFullName").click(function (e) {
        action = "sort";
        e.preventDefault();
        var full_name = $('#full_name').val();
        var group_id = $('#group_id').val();
        var sort_value = changeSortValue(sortingFullNameValue);
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/listUserRest",
            data: {
                action: "sort",
                full_name: full_name,
                group_id: group_id,
                sort_type: "fullName",
                sort_value: sort_value
            },
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                var $tr = '';
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
                $('#tbl_body_id').html($tr);
            },
            error: function (e) {
                alert(e.message);
            }
        });
    });

    function handleClickingAction(actionClick) {
        switch (actionClick) {
            case "search":

        }
    }

    function changeSortValue(sortValue) {
        if (sortValue === "DESC") {
            return sortingFullNameValue = "ASC";
        } else {
            return sortingFullNameValue = "DESC";
        }
    }
});

