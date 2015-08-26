<%-- 
    Document   : index
    Created on : 30-oct-2014, 14:18:53
    Author     : CHRISTIAN-LAP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BPO Group</title>
        <link type="text/css" rel="stylesheet" href="css/main.css" />
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/loginValidator.js" type="text/javascript"></script>
        <script src="js/clock.js" type="text/javascript"></script>
    </head>
    <body id="bd-logueo">
        <div class="encabezado_login">
            <div class="logo">   </div>
        </div>
        <div id="divTimer">
            <span id="fecha"></span>
            <br>
            <span id="reloj"></span>
        </div>
        <div id="sc-login">
            <div id="hmain">Iniciar sesión</div>
            <form action="LoginServlet" method="post">
                <label for="txtUsuario">Usuario</label><br>
                <input type="text" name="txtUsuario" id="txtUsuario" size="20" required autofocus/><br>
                <label for="txtClave">Contraseña</label><br>
                <input type="password" name="txtClave"id="txtClave" size="20" required/><br>
                <input type="submit" value="Iniciar sesión" onclick="return validar()" />
                <input type="hidden" name="accion" value="login"/>
            </form>
            <div id="divError">
                <%= request.getAttribute("mensajeError") == null ? ""
                        : request.getAttribute("mensajeError")%>
            </div>
        </div>
    </body>
</html>
