$(document).ready(function() {
    $('.request-btn').on('click', function(e) {
        e.preventDefault();
        var rowId = $(this).closest('tr').attr('id');
        $(`#${rowId}`).remove();
        // Optional: You can also make an AJAX request to handle the server-side deletion
    });
});