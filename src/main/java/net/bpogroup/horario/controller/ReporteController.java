package net.bpogroup.horario.controller;

import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.service.ReportService;
import net.bpogroup.horario.util.UtilApp;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Christianl3aron on 31/08/2015.
 */
@Controller
public class ReporteController {

    @RequestMapping(value = "/reporte-asistencia")
    public ModelAndView reporteAsistencia(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        UsuarioBean usuarioSession = (UsuarioBean) request.getSession().getAttribute("usuario");
        if (null != usuarioSession && 2 != usuarioSession.getTipoUsuarioBean().getCodigo()) {
            modelAndView.setViewName("reporte");

        } else if (null == usuarioSession) {
            return new ModelAndView("redirect:/");
        } else {
            return new ModelAndView("redirect:/home");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/ajax-reporte", method = RequestMethod.GET)
    public void sendReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger logger = Logger.getLogger(this.getClass());
        PrintWriter out = response.getWriter();
        String accion = request.getParameter("a");
        StringBuilder sb = new StringBuilder();

        Date ti = null;
        Date tf = null;

        try {
            ti = UtilApp.getDate(request.getParameter("ti"));
            tf = UtilApp.addADay(request.getParameter("tf"));
        } catch (Exception e) {
        }
        if (null != accion && null != request.getSession().getAttribute("usuario") && accion.equals("ra")) {
            try {
                ReportService reportService = UtilApp.getContext().getBean(ReportService.class);
                sb.append(reportService.obtenerReporte(ti, tf));

                // Recuperando usuario para generar log
                UsuarioBean usuarioBean = (UsuarioBean) request.getSession().getAttribute("usuario");
                logger.info("Reporte generado por: " + usuarioBean.getDni() + "  " + new Date());
                out.write(sb.toString());
                out.close();

            } catch (Exception e) {
                logger.error(e.toString().replace("\'", ""), e);
            }
        }
    }
}