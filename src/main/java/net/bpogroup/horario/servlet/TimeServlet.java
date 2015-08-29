package net.bpogroup.horario.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bpogroup.horario.model.EstadoBean;
import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.service.imp.HorarioServiceImp;

/**
 * Created by Christianl3aron on 12/03/2015.
 */

public class TimeServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

        String s = request.getParameter("s");
        HttpSession miSesion = request.getSession();
        UsuarioBean usuarioBean = (UsuarioBean) miSesion.getAttribute("usuario");
        HorarioServiceImp horarioService;

        try {
            if (s.equals("ia")) {
                usuarioBean.setEstadoBean(new EstadoBean(2, ""));
                miSesion.setAttribute("usuario", usuarioBean);
                horarioService = new HorarioServiceImp();
                horarioService.iniciarAlmuerzo(usuarioBean.getDni());
                out.println("<script src=\"js/main.js\" type=\"text/javascript\"></script>");
                out.println("<button type=\'button\' id=\"btnij\" style=\"width: 200px;height: 200px;margin-right:30px\" disabled>Inicio jornada</button>");
                out.println("<button type=\'button\' id=\"btnia\" style=\"width: 200px;height: 200px;margin-right:30px\" disabled>Inicio almuerzo</button>");
                out.println("<button type=\'button\' id=\"btnfa\" style=\"width: 200px;height: 200px;margin-right:30px\" >Fin almuerzo</button>");
                out.println("<button type=\'button\' id=\"btnfj\" style=\"width: 200px;height: 200px;margin-right:30px\" disabled>Fin jornada</button>");
            } else if (s.equals("fa")) {
                usuarioBean.setEstadoBean(new EstadoBean(3, ""));
                miSesion.setAttribute("usuario", usuarioBean);
                horarioService = new HorarioServiceImp();
                horarioService.finAlmuerzo(usuarioBean.getDni());
                out.println("<script src=\"js/main.js\" type=\"text/javascript\"></script>");
                out.println("<button type=\'button\' id=\"btnij\" style=\"width: 200px;height: 200px;margin-right:30px\" disabled>Inicio jornada</button>");
                out.println("<button type=\'button\' id=\"btnia\" style=\"width: 200px;height: 200px;margin-right:30px\" disabled>Inicio almuerzo</button>");
                out.println("<button type=\'button\' id=\"btnfa\" style=\"width: 200px;height: 200px;margin-right:30px\" disabled>Fin almuerzo</button>");
                out.println("<button type=\'button\' id=\"btnfj\" style=\"width: 200px;height: 200px;margin-right:30px\" >Fin jornada</button>");
            } else if (s.equals("fj")) {
                usuarioBean.setEstadoBean(new EstadoBean(4, ""));
                miSesion.setAttribute("usuario", usuarioBean);
                horarioService = new HorarioServiceImp();
                horarioService.finJornada(usuarioBean.getDni());
                out.println("<script src=\"js/main.js\" type=\"text/javascript\"></script>");
                out.println("<button type=\'button\' id=\"btnij\" style=\"width: 200px;height: 200px;margin-right:30px\" disabled>Inicio jornada</button>");
                out.println("<button type=\'button\' id=\"btnia\" style=\"width: 200px;height: 200px;margin-right:30px\" disabled>Inicio almuerzo</button>");
                out.println("<button type=\'button\' id=\"btnfa\" style=\"width: 200px;height: 200px;margin-right:30px\" disabled>Fin almuerzo</button>");
                out.println("<button type=\'button\' id=\"btnfj\" style=\"width: 200px;height: 200px;margin-right:30px\" disabled>Fin jornada</button>");
            }
        } catch (Exception e) {

        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }
}
