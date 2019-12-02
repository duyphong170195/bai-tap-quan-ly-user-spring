$(document).ready(function () {
    var sortType = "";
    var sortingFullNameValue = "ASC";
    var sortingLevelValue = "ASC";
    var sortingEndDateValue = "DESC";
    var sortValue = "";
    var totalUsers = 0;
    var limitUser = 3;
    var limitPage = 3;
    var currentPage1 = 1;
    var fullName = "";
    var groupName = "";
    var next = false;
    var previous = false;

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
        ajaxCallGettingUser("","", 0, "", "", "", 1, false, false);
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
       ajaxCallGettingUser("search", fullName, groupName, "", "", "", 1,false, false);
    });
    
    $("#sortFullName").click(function (e) {
        sortType = "fullName";
        e.preventDefault();
        getSearchingValue();
        ajaxCallGettingUser("sort", fullName, groupName, sortType, sortingFullNameValue, "#sortFullName", 1, false, false);
    });

    $("#sortNameLevel").click(function (e) {
        sortType = "nameLevel";
        e.preventDefault();
        getSearchingValue();
        ajaxCallGettingUser("sort", fullName, groupName, sortType, sortingLevelValue, "#sortNameLevel", 1, false, false);
    });

    $("#sortEndDate").click(function (e) {
        sortType = "endDate";
        e.preventDefault();
        getSearchingValue();
        ajaxCallGettingUser("sort", fullName, groupName, sortType, sortingEndDateValue, "#sortEndDate", 1, false, false);
    });


    function handlePage(){
        $("#page0").click(function (e) {
            e.preventDefault();
            getSearchingValue();
            ajaxCallGettingUser("pagination", fullName, groupName, sortType, sortValue, "", $("#page0").text(), false, false);
        });
        $("#page1").click(function (e) {
            e.preventDefault();
            getSearchingValue();
            ajaxCallGettingUser("pagination", fullName, groupName, sortType, sortValue, "", $("#page1").text(), false, false);
        });
        $("#page2").click(function (e) {
            e.preventDefault();
            getSearchingValue();
            ajaxCallGettingUser("pagination", fullName, groupName, sortType, sortValue, "", $("#page2").text(), false, false);
        });
    }
    $("#next").click(function (e) {
        e.preventDefault();
        getSearchingValue();
        ajaxCallGettingUser("pagination", fullName, groupName, sortType, sortValue, "", currentPage1, true, false);
    });
    $("#previous").click(function (e) {
        e.preventDefault();
        getSearchingValue();
        ajaxCallGettingUser("pagination", fullName, groupName, sortType, sortValue, "", currentPage1, false, true);
    });


    var ajaxCallGettingUser = function (action, fullName, groupName, sortType, sortingValue, idSort, currentPage, next, previous) {
        return $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/listUserRest",
            data: {
                action: action,
                fullName: fullName,
                groupId: groupName,
                sortType: sortType,
                sortValue: sortingValue,
                currentPage: currentPage,
                next: next,
                previous : previous
            },
            dataType: 'json',
            timeout: 100000
        }).done(function (data) {
            currentPage1 = data.searchData.currentPage;
            changeIconSort(data.searchData.sortType, data.searchData.sortValue, idSort);
            var $tr = '';
            jQuery.each(data.listUser, function (index, value) {
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


            var $a = '';
            jQuery.each(data.listPage, function (index, value) {
                $a = $a  + "<a href='#' id=page"+ index + ">"+ value + "</a>";
                // $("#page" + index).text(value);
            });
            $('.lbl_paging').html($a);
            handlePage();
        }).fail(function(jqXHR, textStatus, errorThrown) {
            // If fail
            console.log(textStatus + ': ' + errorThrown);
        });
    };

    function getSearchingValue(){
        fullName = $('#full_name').val();
        groupName = $('#group_id').val();
    }

    function resetIconSort(){
        $("#sortFullName").text("▲▽");
        $("#sortNameLevel").text("▲▽");
        $("#sortEndDate").text("△▼");
    }

    function changeIconSort(sortType, sortingValue, idSort) {
        if(idSort != "") {
            sortValue = sortingValue;
            switch (sortType) {
                case "fullName":
                    sortingFullNameValue = sortingValue;
                    sortingLevelValue = "ASC";
                    sortingEndDateValue = "DESC";
                    $("#sortNameLevel").text("▲▽");
                    $("#sortEndDate").text("△▼");
                    break;
                case "nameLevel":
                    sortingLevelValue = sortingValue;
                    sortingFullNameValue = "ASC";
                    sortingEndDateValue = "DESC";
                    $("#sortFullName").text("▲▽");
                    $("#sortEndDate").text("△▼");
                    break;
                case "endDate":
                    sortingEndDateValue = sortingValue;
                    sortingFullNameValue = "ASC";
                    sortingLevelValue = "ASC";
                    $("#sortFullName").text("▲▽");
                    $("#sortNameLevel").text("▲▽");
                    break;
            }
            sortingValue === "DESC" ? $(idSort).text("△▼"):$(idSort).text("▲▽");
        }
    }
});

