<%@ page import="net.bpogroup.horario.dao.bean.UsuarioBean" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Christianl3aron
  Date: 14/03/2015
  Time: 09:23 AM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BPO Group</title>
    <link rel="stylesheet" href="js/chosen/chosen.min.css">
    <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="js/chosen/chosen.jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="css/mantenimiento.css">

    <!-- imports del Clockpicker -->
    <link rel="stylesheet" type="text/css" href="js/clockpicker/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="js/clockpicker/css/bootstrap-clockpicker.min.css">
    <link rel="stylesheet" type="text/css" href="js/clockpicker/css/github.min.css">
    <script type="text/javascript" src="js/clockpicker/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/clockpicker/js/bootstrap-clockpicker.min.js"></script>
    <script type="text/javascript" src="js/clockpicker/js/highlight.min.js"></script>

    <style type="text/css" media="all">
        /* fix rtl for demo */
        .chosen-rtl .chosen-drop {
            left: -9000px;
        }

        .input-group {
            width: 110px;
            height: 34px;
        }

        input[type="text"] {
            height: 34px;
        }

        .pull-center {
            margin-left: auto;
            margin-right: auto;
        }

        @media (min-width: 768px) {
            .container {
                max-width: 730px;
            }
        }

        @media (max-width: 767px) {
            .pull-center {
                float: right;
            }
        }

        tr:hover {
            background: #FCF;
        }

        .tab-mod-asistencia {
            width: 1000px;
            font-family: "Trebuchet MS", Helvetica, sans-serif;

        }

        .tab-mod-asistencia tr {
            height: 44px;
        }

        .tab-mod-asistencia input[type="date"] {
            width: 150px;
            height: 34px;
        }

        thead tr {
            background-color: #00b0e6;
            color: #ffffff;
        }

        .td-large {
            text-align: center;
            width: 280px
        }

        .td-med {
            text-align: center;
            width: 200px
        }

        .td-med input[type="date"] {
            margin: auto;
        }

        .td-small {
            text-align: center;
            width: 130px
        }


    </style>

    <script type="text/javascript">
        $(document).ready(function () {

            var config = {
                '.chosen-select': {},
                '.chosen-select-deselect': {allow_single_deselect: true},
                '.chosen-select-no-single': {disable_search_threshold: 10},
                '.chosen-select-no-results': {no_results_text: 'Oops, nothing found!'},
                '.chosen-select-width': {width: "95%"}
            }
            for (var selector in config) {
                $(selector).chosen(config[selector]);
            }

            $('#btnShow').click(function () {
                //alert($("#sltPersonal").chosen().val());
                $.ajax({
                    type: 'GET',
                    url: 'MantenimientoServlet',
                    data: {
                        act: 'showAsi',
                        ti: $('#timeIni').val(),
                        tf: $('#timeFin').val(),
                        cods: $("#sltPersonal").chosen().val().toString()
                    },
                    success: function (dados) {
                        $('#vista').html(dados);
                        $('#btnSave').show();
                    }
                });
            });

            $('#btnSave').click(function () {

                //alert(ids);
                //alert(vals);

                $.ajax({
                    type: 'GET',
                    url: 'MantenimientoServlet',
                    data: {
                        act: 'saveCha',
                        ids: ids.toString(),
                        vals: vals.toString()
                    },
                    success: function () {
                        alert("Se guardaron los cambios");
                    }
                });

                ids = [];
                vals = [];

            });


        });
    </script>

    <script src="js/clockpicker/js/html5shiv.js"></script>
    <script src="js/clockpicker/js/respond.min.js"></script>


    <%
        UsuarioBean usuarioBean = (UsuarioBean) request.getSession().getAttribute("usuario");
        if (request.getSession(false) == null || request.getSession().getAttribute("usuario") == null || usuarioBean.getTipoUsuarioBean().getCodigo() == 2) {
            response.sendRedirect("index.jsp");
            return;
        }
    %>

    <!--style>
        .mod-asi-container {
            display: block;
            margin: auto;
            width: 700px;
            padding: 15px;
            background-color: #dddddd;

        }

        .mod-asi-container input[type="date"] {
            width: 150px;
        }

        .mod-asi-container button[type="button"] {
            margin-left: 100px;
            width: 120px;
        }

        .mod-asi-container label {
            width: 200px;
        }

        label, input, button {
            height: 34px;
            margin-bottom: 10px;
        }

        label {
            text-align: left;
            line-height: 34px;
            width: 200px;
            float: left;
            padding-right: 20px;
        }

        br {
            clear: left;
        }

        #btnSave {
            display: none;
        }

    </style-->
</head>
<body>

<%@include file="cabecera.jspf" %>
<%
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date today = new Date();
%>


<br/>

<div class="mod-asi-container">
    <label for="timeIni">Fecha Inicio :</label>
    <input type="date" name="timeIni" id="timeIni" value="<%= dateFormat.format(today) %>" min="2014-11-01"/>
    <button type='button' id="btnShow">Ver Registros</button>
    <br/>
    <label for="timeFin">Fecha Fin :</label>
    <input type="date" name="timeFin" id="timeFin" value="<%= dateFormat.format(today) %>" min="2014-11-01"/>
    <button type='button' id="btnSave">Grabar</button>
    <br/>


    <div class="side-by-side clearfix">
        <div>
            <select id="sltPersonal" data-placeholder="Your Favorite Football Team" style="width:350px"
                    class="chosen-select" multiple tabindex="6">
                <option value=""></option>

                <c:forEach items="${requestScope.listaAreas}" var="area">

                    <optgroup label="<c:out value="${area.getDenominacion()}"/>">

                        <c:forEach items="${requestScope.listaUsuarios}" var="usuario">
                            <c:if test="${usuario.getAreaBean().getCodigo()==area.getCodigo()}">
                                <option value="<c:out value="${usuario.getDni()}"/>"><c:out
                                        value="${usuario.getNombres()} ${usuario.getApellidos()}"/></option>
                            </c:if>
                        </c:forEach>
                    </optgroup>
                </c:forEach>
            </select>
        </div>
    </div>

</div>


<div id="vista" style="background-color: #dddddd;width: 1000px;margin: 50px auto"></div>

</body>
</html>
