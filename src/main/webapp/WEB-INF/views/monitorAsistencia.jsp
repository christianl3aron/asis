<%--
  Created by IntelliJ IDEA.
  User: Christianl3aron
  Date: 29/08/2015
  Time: 4:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <link rel="stylesheet" href="../../resources/css/main.css">
    <script src="../../resources/js/jquery-1.11.1.min.js" type="text/javascript"></script>

    <script src="../../resources/js/clock.js" type="text/javascript"></script>

    <title></title>
</head>
<body>
<%@include file="cabecera.jspf" %>
<br/>

<div id="divTimer">
    <span id="fecha"></span>
    <br>
    <span id="reloj"></span>
</div>


<table>

    <c:forEach items="${requestScope.listaAsistencia}" var="asistencia">
        <tr>
            <td><c:out value="${asistencia.getUsuarioBean().getNombres()} ${asistencia.getUsuarioBean().getApellidos()}"/></td>
            <td><c:out value="${asistencia.getIniDia()}"/></td>
            <td><c:out value="${asistencia.getIniBreak()}"/></td>
            <td><c:out value="${asistencia.getFinBreak()}"/></td>
            <td><c:out value="${asistencia.getFinDia()}"/></td>
        </tr>
    </c:forEach>


</table>

</body>
</html>
