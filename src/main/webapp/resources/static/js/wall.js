$(document).ready(function () {
    $('.wall-reply').hide();
    $('.wall-reply-add').click(function () {
        $('.wall-reply').hide();
        $(this).siblings(".wall-reply").show();
    });

    $('textarea#body').keydown(function (e) {
        if (e.ctrlKey && e.keyCode == 13) {
            $(this).parents('form').submit();
        }
    });
});