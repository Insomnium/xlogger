$(document).ready(function () {
    $('textarea#reply-area').hide();
    $('.reply-btn').click(function () {
        $('textarea#reply-area').hide();
        $(this).siblings("textarea#reply-area").show();
        $(this).parentsUntil('div.comment-wrapper').parent().siblings('.comment-wrapper').find('input').attr('type', 'button');
        $(this).attr('type', 'submit');
        // FIXME: e.preventDefault() storing state in data-attr?
    });

    $('textarea').keydown(function (e) {
        if (e.ctrlKey && e.keyCode == 13) {
            $(this).parents('form').submit();
        }
    });
});