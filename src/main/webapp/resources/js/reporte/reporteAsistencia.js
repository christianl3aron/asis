/**
 * Created by Christian Baron on 17/03/2015.
 */
$(document).ready(function () {
    $('#btnReport').click(function () {
        $.ajax({
            type: 'GET',
            url: '/ajax-reporte',
            data: {
                a: 'ra',
                ti: $('#timeIni').val(),
                tf: $('#timeFin').val()
            },
            success: function (dados) {
                $('#descarga').attr('href', dados);
                $('#imgDescarga').show();

            }
        });
    });
});