$(document).ready(function () {
    $('textarea#reply-area').hide();
    $('.reply-btn').click(function () {
        $('textarea#reply-area').hide();
        $(this).siblings("textarea#reply-area").show();
    });

    $('textarea#body').keydown(function (e) {
        if (e.ctrlKey && e.keyCode == 13) {
            $(this).parents('form').submit();
        }
    });
});