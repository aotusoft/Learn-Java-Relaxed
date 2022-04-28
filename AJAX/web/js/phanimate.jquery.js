(function ($) {
    $.fn.phAnim = function (options) {

        var settings = $.extend({}, options),
            label,
            ph;

        function getLabel(input) {
            return $(input).parent().find('label');
        }

        return this.each(function () {

            if (getLabel($(this)).length == 0) {
                if ($(this).attr('placeholder') != undefined) {
                    ph = $(this).attr('placeholder');
                    $(this).attr('placeholder', '');
                    $(this).parent().prepend('<label for=' + $(this).attr('id') + '>' + ph + '</label>');
                }
            } else {
                $(this).attr('placeholder', '');
                if (getLabel($(this)).attr('for') == undefined) {
                    getLabel($(this)).attr('for', $(this).attr('id'));
                }
            }
            $(this).on('focus', function () {
                label = getLabel($(this));
                label.addClass('active focusIn');
            }).on('focusout', function () {
                if ($(this).val() == '') {
                    label.removeClass('active');
                }
                label.removeClass('focusIn');
            });
        });
    };
}(jQuery));

$(document).ready(function () {
    $('input').phAnim();
});