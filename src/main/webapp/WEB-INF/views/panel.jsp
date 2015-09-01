<%-- 
    Document   : panel
    Created on : 30-oct-2014, 17:30:25
    Author     : CHRISTIAN-LAP
--%>

<%@page import="net.bpogroup.horario.model.UsuarioBean"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BPO Group</title>
        <link type="text/css" rel="stylesheet" href="../../resources/css/main.css" />
        <link rel="stylesheet" href="../../resources/css/styles.css">
        <script src="../../resources/js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="../../resources/js/clock.js" type="text/javascript"></script>
        <!--%
            // LA SEGURIDAD
            if (request.getSession(false) == null || request.getSession().getAttribute("usuario") == null) {
                response.sendRedirect("index.jsp");
                return;
            }
        %-->
    </head>
    <body>
        <%@include file="cabecera.jspf" %>


        <div id="divTimer">
            <span id="fecha"></span>
            <br>
            <span id="reloj"></span>
        </div>

        <div id="divbotones">

            <script src="../../resources/js/main.js" type="text/javascript"></script>
            <%--<c:choose>
                <c:when test="${sessionScope.usuario.estadoBean.codigo == 4}">
                    <button type='button' id="btnij" style="width: 200px;height: 200px;margin-right:30px" >inicio jornada</button>
                </c:when>
    <c:otherwise>--%>
            <button type='button' id="btnij" style="width: 200px;height: 200px;margin-right:30px" disabled>Inicio jornada</button>
            <%--</c:otherwise>
        </c:choose>--%>
            <c:choose>
                <c:when test="${sessionScope.usuario.estadoBean.codigo == 1}">
                    <button type='button' id="btnia" style="width: 200px;height: 200px;margin-right:30px" >Inicio almuerzo</button>
                </c:when>
                <c:otherwise>
                    <button  type='button' id="btnia" style="width: 200px;height: 200px;margin-right:30px" disabled>Inicio almuerzo</button>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${sessionScope.usuario.estadoBean.codigo == 2}">
                    <button type='button' id="btnfa" style="width: 200px;height: 200px;margin-right:30px">Fin almuerzo</button>
                </c:when>
                <c:otherwise>
                    <button type='button' id="btnfa" style="width: 200px;height: 200px;margin-right:30px" disabled>Fin almuerzo</button>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${sessionScope.usuario.estadoBean.codigo == 3}">
                    <button type='button' id="btnfj" style="width: 200px;height: 200px;margin-right:30px">Fin jornada</button>
                </c:when>
                <c:otherwise>
                    <button type='button' id="btnfj" style="width: 200px;height: 200px;margin-right:30px" disabled>Fin jornada</button>
                </c:otherwise>
            </c:choose>
        </div>


    </body>
</html>
