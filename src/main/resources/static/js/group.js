$(document).ready(function() {
    alert("a");
    $.ajax({
        type:"GET",
        url: "/listGroup"
    }).then(function(data) {
        $('.greeting-id').append(data.group_id);
        $('.greeting-content').append(data.group_name);
    });
});