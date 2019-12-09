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
    });

    $('#btn_search').click(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        $("#btn_search").prop("disabled", false);
       getSearchingValue();
       ajaxSearchUser("search", fullName, groupName);
       // ajaxCallGettingUser("search", fullName, groupName, "", "", "", 1,false, false);
    });

    $(".sort").click(function (e) {
        var kindOfSort = $(this).attr('id');
        switch (kindOfSort) {
            case "sortFullName": sortValue = sortingFullNameValue;
                break;
            case "sortNameLevel": sortValue = sortingLevelValue;
                break;
            case "sortEndDate": sortValue = sortingEndDateValue;
                break;
        }
        e.preventDefault();
        getSearchingValue();
        ajaxCallGettingUser("sort", fullName, groupName, kindOfSort, sortValue, "#" + kindOfSort, 1, false, false);
    });



    // function handlePage(){
    $(".lbl_paging").click(function (e) {
        var pageNumberId = "#" + e.target.id;
        e.preventDefault();
        getSearchingValue();
        if(pageNumberId === "#next") {
            ajaxCallGettingUser("pagination", fullName, groupName, sortType, sortValue, "", currentPage1, true, false);
        } else if(pageNumberId === "#previous") {
            ajaxCallGettingUser("pagination", fullName, groupName, sortType, sortValue, "", currentPage1, false, true);
        } else {
            ajaxCallGettingUser("pagination", fullName, groupName, sortType, sortValue, "", $(pageNumberId).text(), false, false);
        }
    });


    var ajaxSearchUser = function(action, fullName, groupName) {
        return $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/listUserRest",
            data: {
                action: action,
                fullName: fullName,
                groupId: groupName
            },
            dataType: 'json',
            timeout: 100000
        }).done(function (data) {
            addRowUserToTable(data.listUser);
            addPageToFooter(data.listPage);
            hiddenOrShowNextPrevious(data.searchData);
            totalUsers = data.totalUser;
            currentPage1 = data.searchData.currentPage;
        }).fail(function(jqXHR, textStatus, errorThrown) {
            // If fail
            console.log(textStatus + ': ' + errorThrown);
        });
    };

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
            addRowUserToTable(data.listUser);
            addPageToFooter(data.listPage);
            hiddenOrShowNextPrevious(data.searchData);
            totalUsers = data.totalUser;
            currentPage1 = data.searchData.currentPage;
            changeIconSort(data.searchData.sortType, data.searchData.sortValue);
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

    function addRowUserToTable(listUser) {
        var $tr = '';
        jQuery.each(listUser, function (index, value) {
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
    function addPageToFooter(listPage) {
        var $a = '';
        jQuery.each(listPage, function (index, value) {
            $a = $a  + "<a href='#' id=page"+ index + ">"+ value + "</a>";
        });
        $('#pages_region').html($a);
    }
    function hiddenOrShowNextPrevious(data){
        if(data.hiddenPrevious) {
            $('#previous').hide();
        }else {
            $('#previous').show();
        }
        if(data.hiddenNext) {
            $('#next').hide();
        }else {
            $('#next').show();
        }
    }

    function changeIconSort(sortType, sortingValue) {
        switch (sortType) {
            case "sortFullName":
                sortingFullNameValue = sortingValue;
                sortingLevelValue = "ASC";
                sortingEndDateValue = "DESC";
                $("#sortNameLevel").text("▲▽");
                $("#sortEndDate").text("△▼");
                break;
            case "sortNameLevel":
                sortingLevelValue = sortingValue;
                sortingFullNameValue = "ASC";
                sortingEndDateValue = "DESC";
                $("#sortFullName").text("▲▽");
                $("#sortEndDate").text("△▼");
                break;
            case "sortEndDate":
                sortingEndDateValue = sortingValue;
                sortingFullNameValue = "ASC";
                sortingLevelValue = "ASC";
                $("#sortFullName").text("▲▽");
                $("#sortNameLevel").text("▲▽");
                break;
        }
        sortingValue === "DESC" ? $("#" + sortType).text("△▼"):$("#" + sortType).text("▲▽");
    }
});

