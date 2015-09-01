package net.bpogroup.horario.controller;

import net.bpogroup.horario.model.EstadoBean;
import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.service.HorarioService;
import net.bpogroup.horario.service.LoginService;
import net.bpogroup.horario.util.UtilApp;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Christianl3aron on 30/08/2015.
 */
@Controller
@SessionAttributes("usuario")
public class LoginController {

    @RequestMapping("home")
    public ModelAndView loguIn(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        String accion = request.getParameter("accion");
        Logger logger = Logger.getLogger(this.getClass());

        UsuarioBean userSession = (UsuarioBean) request.getSession().getAttribute("usuario");

        if (null != accion && accion.equals("login")) {
            try {
                // Recuperando valores del formulario
                String txtUser = request.getParameter("txtUsuario");
                String txtPass = request.getParameter("txtClave");
                LoginService loginService = UtilApp.getContext().getBean(LoginService.class);
                UsuarioBean usuarioBean = null;
                Date fecha = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                // Validando que los valores sean iguales
                if (txtUser.equals(txtPass)) {
                    // Recuperando datos de usuario
                    usuarioBean = loginService.validarUsuario(txtUser);
                }
                // Si no hay resultados
                if (usuarioBean == null) {
                    modelAndView.addObject("mensajeError", "Ingrese bien su usuario y contraseña!!!");
                    modelAndView.setViewName("index");

                }// Si "ultima fecha de inicio" es hoy y "ultima fecha de inicio" es menor a "ultima fecha fin"
                else if (dateFormat.format(fecha).equals(dateFormat.format(usuarioBean.getUlt_inicio())) && usuarioBean.getUlt_inicio().before(usuarioBean.getUlt_fin())) {
                    modelAndView.addObject("mensajeError", "ya ingreso hoy !!!");
                    modelAndView.setViewName("index");

                }// Si "ultima fecha de inicio" es diferente a "hoy", Primer inicio de sesion en el dia
                else if (!dateFormat.format(fecha).equals(dateFormat.format(usuarioBean.getUlt_inicio()))) {
                    usuarioBean.setEstadoBean(new EstadoBean(1, ""));
                    HorarioService horarioService = UtilApp.getContext().getBean(HorarioService.class);
                    horarioService.iniciarJornada(usuarioBean.getDni());

                    modelAndView.addObject("usuario", usuarioBean);
                    modelAndView.setViewName("panel");

                }// Redireccionar al panel paa que genere más eventos
                else {
                    modelAndView.addObject("usuario", usuarioBean);
                    modelAndView.setViewName("panel");
                }

            } catch (Exception e) {
                // Redireccionar a pagina de inicio
                logger.error(e.toString().replace("\'", ""), e);
                modelAndView.addObject("mensajeError", "Falla de sistema !!!");
                modelAndView.setViewName("panel");
            }

        } else if (null != userSession && 4 != userSession.getEstadoBean().getCodigo()) {
            try {
                LoginService loginService = UtilApp.getContext().getBean(LoginService.class);
                userSession = loginService.validarUsuario(userSession.getDni());
                modelAndView.addObject("usuario", userSession);
            } catch (Exception e) {
            }
            modelAndView.setViewName("panel");
        } else {
            return new ModelAndView("redirect:/");
        }
        return modelAndView;
    }
}
