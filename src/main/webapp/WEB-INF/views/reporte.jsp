<%-- 
    Document   : reporte
    Created on : 11-nov-2014, 14:15:17
    Author     : CHRISTIAN-LAP
--%>

<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DateFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BPO Group</title>
    <script src="../../resources/js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="../../resources/js/reporte/reporteAsistencia.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="../../resources/css/reporte.css"/>
    <link rel="stylesheet" type="text/css" href="../../resources/js/clockpicker/css/bootstrap.min.css">

</head>
<body>
<%@include file="cabecera.jspf" %>
<%
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date today = new Date();
%>
<br>

<div class="rep-asi-container">
    <label for="timeIni">Fecha Inicio :</label>
    <input type="date" name="timeIni" id="timeIni" value="<%= dateFormat.format(today) %>"
           min="2014-11-01"/>
    <button type='button' id="btnReport">Ver reporte</button>
    <br>
    <label for="timeFin">Fecha Fin :</label>
    <input type="date" name="timeFin" id="timeFin" value="<%= dateFormat.format(today) %>"
           min="2014-11-01"/>
    <a id="descarga" target="_blank" download><img id="imgDescarga" src="../../resources/img/download.png" alt="descargar"/></a>

</div>

</body>
</html>
