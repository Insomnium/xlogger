$(document).ready(function () {
    $('.wall-comment').hide();
    $('.wall-comment-add').click(function () {
        $('.wall-comment').hide();
        $(this).siblings(".wall-comment").show();
    })
});