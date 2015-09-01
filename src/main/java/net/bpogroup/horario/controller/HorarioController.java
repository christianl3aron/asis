package net.bpogroup.horario.controller;

import net.bpogroup.horario.model.EstadoBean;
import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.service.HorarioService;
import net.bpogroup.horario.util.UtilApp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by Christianl3aron on 30/08/2015.
 */
@Controller
public class HorarioController {

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public void generateEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String s = request.getParameter("s");
        HttpSession miSesion = request.getSession();
        UsuarioBean usuarioBean = (UsuarioBean) miSesion.getAttribute("usuario");
        HorarioService horarioService = UtilApp.getContext().getBean(HorarioService.class);

        try {
            if (null != s && 1 == usuarioBean.getEstadoBean().getCodigo() && s.equals("ia")) {
                usuarioBean.setEstadoBean(new EstadoBean(2, ""));
                miSesion.setAttribute("usuario", usuarioBean);
                horarioService.iniciarAlmuerzo(usuarioBean.getDni());
                out.close();

            } else if (null != s && 2 == usuarioBean.getEstadoBean().getCodigo() && s.equals("fa")) {
                usuarioBean.setEstadoBean(new EstadoBean(3, ""));
                miSesion.setAttribute("usuario", usuarioBean);
                horarioService.finAlmuerzo(usuarioBean.getDni());
                out.close();

            } else if (null != s && 3 == usuarioBean.getEstadoBean().getCodigo() && s.equals("fj")) {
                usuarioBean.setEstadoBean(new EstadoBean(4, ""));
                miSesion.setAttribute("usuario", usuarioBean);
                horarioService.finJornada(usuarioBean.getDni());
                out.close();
            }
        } catch (Exception e) {
        }
    }
}
