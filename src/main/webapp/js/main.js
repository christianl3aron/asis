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
            url: 'TimeServlet',
            data: {
                s:'ia'
            },
            success: function(dados){
                alert("Ha iniciado su break !!!");
                $('#divbotones').html(dados);
            }
        }); 
    });
    $('#btnfa').click(function(){
        $.ajax({
            type: 'GET',
            url: 'TimeServlet',
            data: {
                s:'fa'
            },
            success: function(dados){
                alert("Ha finalizado su break !!!");
                $('#divbotones').html(dados);
            }
        }); 
    });
    $('#btnfj').click(function(){
        $.ajax({
            type: 'GET',
            url: 'TimeServlet',
            data: {s:'fj'},
            success: function(dados){
                alert("Ha finalizado su jornada!!");
                $('#divbotones').html(dados);
                window.location.replace("/bpogroup_control/index.jsp");
            }
        }); 
    });
});


            

    

