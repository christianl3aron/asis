$(document).ready(function(){
    /*$('#btnij').click(function(){
        $.ajax({
            type: 'GET',
            url: 'TimeServlet',
            data: {
                s:'ij'
            },
            success: function(dados){
                alert("Ha iniciado su jornada !!!");
                $('#divbotones').html(dados);
            }
        });
    });*/
    $('#btnia').click(function(){
        $.ajax({
            type: 'GET',
            url: 'event',
            data: {
                s:'ia'
            },
            success: function(dados){
                alert("Ha iniciado su break !!!");
                $('#btnia').attr("disabled", true);
                $('#btnfa').attr("disabled", false);
            }
        });
    });
    $('#btnfa').click(function(){
        $.ajax({
            type: 'GET',
            url: 'event',
            data: {
                s:'fa'
            },
            success: function(dados){
                alert("Ha finalizado su break !!!");
                $('#btnfa').attr("disabled", true);
                $('#btnfj').attr("disabled", false);
            }
        });
    });
    $('#btnfj').click(function(){
        $.ajax({
            type: 'GET',
            url: 'event',
            data: {s:'fj'},
            success: function(dados){
                alert("Ha finalizado su jornada!!");
                $('#btnfj').attr("disabled", true);
                window.location.replace("/inicio");
            }
        });
    });
});


            

    

