function changeGetParam(uri, key, value) {
    var re = new RegExp("([?&])" + key + "=.*?(&|$)", "i");
    var delim = uri.indexOf('?') !== -1 ? "&" : "?";
    if (uri.match(re)) {
        return uri.replace(re, '$1' + key + "=" + value + '$2');
    }
    else {
        return uri + delim + key + "=" + value;
    }
}

$(document).ready(function() {
    $('#locale_selector').change(function () {
        window.location.replace(changeGetParam(window.location.href, 'lang', $(this).val()));
    });
});