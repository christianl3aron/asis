$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: 'clock',
        data: {
            sol: 'fecha'
        },
        success: function (dados) {
            var pegadados = dados.split("-");
            $('#fecha').text(pegadados[0]);
            $('#reloj').text(pegadados[1]);
            mueveReloj();
        }
    });
});

function mueveReloj() {
    tiempo = $('#reloj').text().split(":");
    tiempo[2] = parseInt(tiempo[2]) + 1;
    //alert(tiempo[2]);

    momentoActual = new Date();
    momentoActual.setHours(tiempo[0], tiempo[1], tiempo[2]);
    tiempo[0] = momentoActual.getHours();
    minuto = momentoActual.getMinutes();
    segundo = momentoActual.getSeconds();

    str_segundo = new String(segundo);
    if (str_segundo.length == 1)
        segundo = "0" + segundo;

    str_minuto = new String(minuto);
    if (str_minuto.length == 1)
        minuto = "0" + minuto;

    str_hora = new String(tiempo[0]);
    if (str_hora.length == 1)
        tiempo[0] = "0" + tiempo[0];

    horaImprimible = tiempo[0] + " : " + minuto + " : " + segundo;

    $('#reloj').text(horaImprimible);
    setTimeout("mueveReloj()", 1000);
} 