/**
 * Created by Christianl3aron on 16/03/2015.
 */
$(document).ready(function () {
    $('#btnShow').click(function () {

        alert($("#sltPersonal").chosen().val());

        $.ajax({
            type: 'GET',
            url: 'MantenimientoServlet',
            data: {
                act: 'modAsi',
                ti: $('#timeIni').val(),
                tf: $('#timeFin').val(),
                cods: $("#sltPersonal").chosen().val().toString()
            },
            success: function (dados) {
                $('#vista').html(dados);
            }
        });
    });
});