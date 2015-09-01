package net.bpogroup.horario.controller;

import net.bpogroup.horario.model.AreaBean;
import net.bpogroup.horario.model.AsistenciaBean;
import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.service.MantenimientoService;
import net.bpogroup.horario.service.MonitorService;
import net.bpogroup.horario.util.UtilApp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Christianl3aron on 1/09/2015.
 */
@Controller
public class MonitorController {
    @RequestMapping(value = "monitor")
    public ModelAndView loanManteniminetoAsistencia(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        UsuarioBean usuarioSession = (UsuarioBean) request.getSession().getAttribute("usuario");

        if (null != usuarioSession && 2 != usuarioSession.getTipoUsuarioBean().getCodigo()) {
            MonitorService monitorService = UtilApp.getContext().getBean(MonitorService.class);
            List<AsistenciaBean> list = null;
            try {
                list = monitorService.listAsistenciasParaMonitor();
            } catch (Exception e) {
            }
            request.setAttribute("listaAsistencia", list);
            modelAndView.setViewName("monitorAsistencia");
        } else if (null == usuarioSession) {
            return new ModelAndView("redirect:/");
        } else {
            return new ModelAndView("redirect:/home");
        }
        return modelAndView;
    }
}
