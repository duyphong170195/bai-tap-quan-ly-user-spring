$(document).ready(function () {
    var action = "";
    var sortingFullNameValue = "ASC";
    var sortingLevelValue = "ASC";
    var sortingEndDateValue = "DESC";

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
        return false;
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
                var $tr = "<tr class ='tr2'>"
                    +  "<th align='center' width='20px'>ID</th>"
                    +  "<th align='left'>氏名 <a href = '' id='sortFullName'>▲▽</a></th>"
                    +  "<th align='left'>生年月日</th>"
                    +  "<th align='left'>グループ</th>"
                    +  "<th align='left'>メールアドレス</th>"
                    +  "<th align='left'>電話番号</th>"
                    +  "<th align='left'>日本語能力 <a href = ''>▲▽</a></th>"
                    +  "<th align='left'>失効日 <a href = ''>△▼</a></th>"
                    +  "<th align='left'>点数</th>"
                    +"</tr>";
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
                alert(e.message);
            }
        });
        return false;
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
                var $tr = "<tr class ='tr2'>"
                    +  "<th align='center' width='20px'>ID</th>"
                    +  "<th align='left'>氏名 <a href='' id='sortFullName'>▲▽</a></th>"
                    +  "<th align='left'>生年月日</th>"
                    +  "<th align='left'>グループ</th>"
                    +  "<th align='left'>メールアドレス</th>"
                    +  "<th align='left'>電話番号</th>"
                    +  "<th align='left'>日本語能力 <a href = ''>▲▽</a></th>"
                    +  "<th align='left'>失効日 <a href = ''>△▼</a></th>"
                    +  "<th align='left'>点数</th>"
                    +"</tr>";

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
            },
            error: function(e){
                alert(e.message);
            }
        });
        return false;
    });

    function handleClickingAction(actionClick){
        switch (actionClick) {
            case "search":

        }
    }

    function changeSortValue(sortValue) {
        if(sortValue === "DESC"){
            sortingFullNameValue = "ASC";
            sortingLevelValue = "ASC";
            sor
        } else {
            sortingFullNameValue = "DESC";
        }
    }
});

