$(document).ready(function () {
    var sortType = "";
    var sortingFullNameValue = "ASC";
    var sortingLevelValue = "ASC";
    var sortingEndDateValue = "DESC";
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
        ajaxCallGettingUser("", "", "", "", "", "", "", 1, false, false);
    });

    $('#btn_search').click(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        $("#btn_search").prop("disabled", false);
        getSearchingValue();
        ajaxCallGettingUser("search", fullName, groupName, "", "", "", "", 1, false, false);
    });

    $(".sort").click(function (e) {
        var kindOfSort = $(this).attr('id');
        e.preventDefault();
        getSearchingValue();
        ajaxCallGettingUser("sort", fullName, groupName, kindOfSort, sortingFullNameValue, sortingLevelValue, sortingEndDateValue, 1, false, false);
    });


    // function handlePage(){
    $(".lbl_paging").click(function (e) {
        var pageNumberId = "#" + e.target.id;
        e.preventDefault();
        getSearchingValue();
        if (pageNumberId === "#next") {
            ajaxCallGettingUser("pagination", fullName, groupName, sortType, sortingFullNameValue, sortingLevelValue, sortingEndDateValue, currentPage, true, false);
        } else if (pageNumberId === "#previous") {
            ajaxCallGettingUser("pagination", fullName, groupName, sortType, sortingFullNameValue, sortingLevelValue, sortingEndDateValue, currentPage, false, true);
        } else {
            ajaxCallGettingUser("pagination", fullName, groupName, sortType, sortingFullNameValue, sortingLevelValue, sortingEndDateValue, $(pageNumberId).text(), false, false);
        }
    });

    var ajaxCallGettingUser = function (action, fullName, groupName, sortType, sortNameValueParameter, sortLevelValueParameter, sortEndDateValueParameter, currentPageParameter, next, previous) {
        return $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/listUserRest",
            data: {
                action: action,
                fullName: fullName,
                groupId: groupName,
                sortType: sortType,
                sortNameValue: sortNameValueParameter,
                sortLevelValue: sortLevelValueParameter,
                sortEndDateValue: sortEndDateValueParameter,
                currentPage: currentPageParameter,
                next: next,
                previous: previous
            },
            dataType: 'json',
            timeout: 100000
        }).done(function (data) {
            setCurrentSortingValue(data.searchData.sortNameValue, data.searchData.sortLevelValue, data.searchData.sortEndDateValue);
            changeIconSort(data.searchData.sortNameValue, data.searchData.sortLevelValue, data.searchData.sortEndDateValue);
            addRowUserToTable(data.listUser);
            addPageToFooter(data.listPage);
            hiddenOrShowNextPrevious(data.searchData);
            currentPage = data.searchData.currentPage;
        }).fail(function (jqXHR, textStatus, errorThrown) {
            // If fail
            console.log(textStatus + ': ' + errorThrown);
            console.log(jqXHR.responseText);
            console.log(jqXHR.responseJSON);
            console.log(jqXHR.responseJSON.detail);
        });
    };

    function getSearchingValue() {
        fullName = $('#full_name').val();
        groupName = $('#group_id').val();
    }

    function setCurrentSortingValue(sortingFullNameValueParameter, sortingLevelValueParameter, sortingEndDateParameter) {
        sortingFullNameValue = sortingFullNameValueParameter;
        sortingLevelValue = sortingLevelValueParameter;
        sortingEndDateValue = sortingEndDateParameter;
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
            $a = $a + "<a href='#' id=page" + index + ">" + value + "</a>";
        });
        $('#pages_region').html($a);
    }

    function hiddenOrShowNextPrevious(data) {
        if (data.hiddenPrevious) {
            $('#previous').hide();
        } else {
            $('#previous').show();
        }
        if (data.hiddenNext) {
            $('#next').hide();
        } else {
            $('#next').show();
        }
    }

    function changeIconSort(sortingFullNameValueParameter, sortingLevelValueParameter, sortingEndDateValueParameter) {
        if (sortingFullNameValueParameter === "ASC") {
            $("#sortFullName").text("▲▽");
        } else {
            $("#sortFullName").text("△▼");
        }
        if (sortingLevelValueParameter === "ASC") {
            $("#sortNameLevel").text("▲▽");
        } else {
            $("#sortNameLevel").text("△▼");
        }
        if (sortingEndDateValueParameter === "ASC") {
            $("#sortEndDate").text("▲▽");
        } else {
            $("#sortEndDate").text("△▼");
        }
    }
});

