$(document).ready(function () {
    var sortType = "";
    var sortingFullNameValue = "ASC";
    var sortingLevelValue = "ASC";
    var sortingEndDateValue = "DESC";
    var totalUsers = 0;
    var limitUser = 3;
    var limitPage = 3;
    var currentPage = 1;
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
        ajaxCallGettingUser("", 0, "", "", "");
    }).then(function () {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/getTotalUser",
            success: function (data) {
                totalUsers = data;
            },
            error: function (e) {
                var error = JSON.parse(e.responseText);
                alert(error.apierror.message);
            }
        })
    });

    $('#btn_search').click(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        $("#btn_search").prop("disabled", false);

       getSearchingValue();
       ajaxCallGettingUser(fullName, groupName, "", "", "");
    });
    
    $("#sortFullName").click(function (e) {
        sortType = "fullName";
        e.preventDefault();
        getSearchingValue();
        ajaxCallGettingUser(fullName, groupName, sortType, sortingFullNameValue, "#sortFullName");
    });

    $("#sortNameLevel").click(function (e) {
        sortType = "nameLevel";
        e.preventDefault();
        getSearchingValue();
        ajaxCallGettingUser(fullName, groupName, sortType, sortingLevelValue, "#sortNameLevel");
    });

    $("#sortEndDate").click(function (e) {
        sortType = "endDate";
        e.preventDefault();
        getSearchingValue();
        ajaxCallGettingUser(fullName, groupName, sortType, sortingEndDateValue, "#sortEndDate");
    });

    var ajaxCallGettingUser = function (fullName, groupName, sortType, sortingValue, idSort) {
        return $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/listUserRest",
            data: {
                full_name: fullName,
                group_id: groupName,
                sort_type: sortType,
                sort_value: changeSortValue(sortType, sortingValue, idSort),
                current_page: currentPage,
                limit_user: limitUser
            },
            dataType: 'json',
            timeout: 100000
        }).done(function (data) {
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
        }).fail(function(jqXHR, textStatus, errorThrown) {
            // If fail
            console.log(textStatus + ': ' + errorThrown);
        });
    };

    function getSearchingValue(){
        fullName = $('#full_name').val();
        groupName = $('#group_id').val();
    }

    function changeSortValue(sortType, sortValue, idSort) {
        sortingFullNameValue = "ASC";
        sortingLevelValue = "ASC";
        sortingEndDateValue = "DESC";
        switch (sortType) {
            case "fullName":
                $("#sortNameLevel").text("▲▽");
                $("#sortEndDate").text("△▼");
                if (sortValue === "DESC") {
                    $(idSort).text("▲▽");
                    return sortingFullNameValue = "ASC"
                } else {
                    $(idSort).text("△▼");
                    return sortingFullNameValue = "DESC";
                }
                break;
            case "nameLevel":
                $("#sortFullName").text("▲▽");
                $("#sortEndDate").text("△▼");
                if (sortValue === "DESC") {
                    $(idSort).text("▲▽");
                    return sortingLevelValue = "ASC"
                } else {
                    $(idSort).text("△▼");
                    return sortingLevelValue = "DESC";
                }
                break;
            case "endDate":
                $("#sortFullName").text("▲▽");
                $("#sortNameLevel").text("▲▽");
                if (sortValue === "DESC") {
                    $(idSort).text("▲▽");
                    return sortingEndDateValue = "ASC"
                } else {
                    $(idSort).text("△▼");
                    return sortingEndDateValue = "DESC";
                }
                break;
            default:
                $("#sortFullName").text("▲▽");
                $("#sortNameLevel").text("▲▽");
                $("#sortEndDate").text("△▼");
                break;
        }
    }

    function getTotalPage(totalUser, limitUser) {
        return Math.ceil(totalUser/limitUser);
    }
});

