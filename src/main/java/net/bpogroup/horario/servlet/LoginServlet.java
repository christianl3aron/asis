package net.bpogroup.horario.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bpogroup.horario.model.EstadoBean;
import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.service.imp.HorarioServiceImp;
import net.bpogroup.horario.service.imp.LoginServiceImp;
import org.apache.log4j.Logger;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");
        Logger logger = Logger.getLogger(this.getClass());

        if(accion.equals("login")) {
            try {
                // Recuperando valores del formulario
                String ex = request.getParameter("txtUsuario");
                String dispa3 = request.getParameter("txtClave");

                LoginServiceImp loginService = new LoginServiceImp();
                UsuarioBean usuarioBean = null;

                Date fecha = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                // Validando que los valores sean iguales
                if(ex.equals(dispa3)) {
                    // Recuperando datos de usuario
                    usuarioBean = loginService.validarUsuario(ex);
                }

                RequestDispatcher miSesion;
                if(usuarioBean == null) {
                    // Si no hay resultados
                    request.setAttribute("mensajeError", "Ingrese bien su usuario y contraseña!!!");
                    miSesion = this.getServletContext().getRequestDispatcher("/index.jsp");
                    miSesion.forward(request, response);

                } else if(dateFormat.format(fecha).equals(dateFormat.format(usuarioBean.getUlt_inicio())) && usuarioBean.getUlt_inicio().before(usuarioBean.getUlt_fin())) {
                    // Si "ultima fecha de inicio" es hoy y "ultima fecha de inicio" es menor a "ultima fecha fin"
                    request.setAttribute("mensajeError", "ya ingreso hoy !!!");
                    miSesion = this.getServletContext().getRequestDispatcher("/index.jsp");
                    miSesion.forward(request, response);

                } else if(!dateFormat.format(fecha).equals(dateFormat.format(usuarioBean.getUlt_inicio()))) {
                    // Si "ultima fecha de inicio" es diferente a "hoy", Primer inicio de sesion en el dia
                    usuarioBean.setEstadoBean(new EstadoBean(1, ""));
                    HorarioServiceImp miSesion1 = new HorarioServiceImp();
                    miSesion1.iniciarJornada(usuarioBean.getDni());
                    HttpSession dispa1 = request.getSession(true);
                    dispa1.setAttribute("usuario", usuarioBean);
                    RequestDispatcher dispa2 = this.getServletContext().getRequestDispatcher("/panel.jsp");
                    dispa2.forward(request, response);

                } else {
                    // Redireccionar al panel paa que genere más eventos
                    HttpSession miSesion2 = request.getSession(true);
                    miSesion2.setAttribute("usuario", usuarioBean);
                    RequestDispatcher dispa4 = this.getServletContext().getRequestDispatcher("/panel.jsp");
                    dispa4.forward(request, response);
                }
            } catch (Exception e) {
                // Redireccionar a pagina de inicio
                logger.error(e.toString().replace("\'", ""), e);
                request.setAttribute("mensajeError", "Falla de sistema !!!");
                RequestDispatcher dispa = this.getServletContext().getRequestDispatcher("/index.jsp");
                dispa.forward(request, response);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }
}