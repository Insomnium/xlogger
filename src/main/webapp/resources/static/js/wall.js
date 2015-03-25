$(document).ready(function () {
    $('.wall-comment').hide();
    $('.wall-comment-add').click(function () {
        $('.wall-comment').hide();
        $(this).siblings(".wall-comment").show();
    });

    $('textarea#body').keydown(function (e) {
        if (e.ctrlKey && e.keyCode == 13) {
            $(this).parents('form').submit();
        }
    });
});