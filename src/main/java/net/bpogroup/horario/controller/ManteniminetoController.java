package net.bpogroup.horario.controller;

import net.bpogroup.horario.model.AreaBean;
import net.bpogroup.horario.model.AsistenciaBean;
import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.service.MantenimientoService;
import net.bpogroup.horario.service.ReportService;
import net.bpogroup.horario.util.UtilApp;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Christianl3aron on 1/09/2015.
 */
@Controller
public class ManteniminetoController {

    @RequestMapping(value = "/mantenimiento-asistencia")
    public ModelAndView loanManteniminetoAsistencia(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        UsuarioBean usuarioSession = (UsuarioBean) request.getSession().getAttribute("usuario");

        if (null != usuarioSession && 2 != usuarioSession.getTipoUsuarioBean().getCodigo()) {
            MantenimientoService mantenimientoService = UtilApp.getContext().getBean(MantenimientoService.class);
            List<UsuarioBean> listaUsuarios = null;
            List<AreaBean> listaAreas = null;

            try {
                listaUsuarios = mantenimientoService.getUsuariosParaCombobox();
                listaAreas = mantenimientoService.getAreaParaCombobox();
            } catch (Exception e) {
            }
            modelAndView.addObject("listaUsuarios", listaUsuarios);
            modelAndView.addObject("listaAreas", listaAreas);
            modelAndView.setViewName("modificaAsistencia");
        } else if (null == usuarioSession) {
            return new ModelAndView("redirect:/");
        } else {
            return new ModelAndView("redirect:/home");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/mantenimiento-event", method = RequestMethod.GET)
    public void sendReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger logger = Logger.getLogger(this.getClass());
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("a");

        if (accion.equals("showAsi")) {
            // Si la accion es showAsi("Show Asistencia") enviar List<AsistenciaBena> al div
            try {
                String listaDni = request.getParameter("cods");
                Date ti = UtilApp.getDate(request.getParameter("ti"));
                Date tf = UtilApp.addADay(request.getParameter("tf"));

                MantenimientoService mantenimientoService = UtilApp.getContext().getBean(MantenimientoService.class);
                List<AsistenciaBean> la = mantenimientoService.getAsistenciasPorUsuarios(ti, tf, listaDni);
                if (la.size() > 0) {
                    out.println("<table class=\"tab-mod-asistencia\">");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<td class=\"td-large\">Nombres y apellidos</th>");
                    out.println("<td class=\"td-med\">dia</th>");
                    out.println("<td class=\"td-small\">Inicio</th>");
                    out.println("<td class=\"td-small\">Inicio Break</th>");
                    out.println("<td class=\"td-small\">Finalizo Break</th>");
                    out.println("<td class=\"td-small\">Fin</th>");
                    out.println("</tr>");
                    out.println("</thead>");

                }
                for (int i = 0; i < la.size(); i++) {
                    out.println("<tr>");
                    out.println("<td class=\"td-large\">" + la.get(i).getUsuarioBean().getNombres() + " " + la.get(i).getUsuarioBean().getApellidos() + "</td>");
                    out.println("<td class=\"td-med\"><input type=\"date\" value=\"" + la.get(i).getDia() + "\" min=\"2014-11-01\" disabled=\"\"></td>");
                    out.println("<td class=\"td-small\"><div class=\"input-group clockpicker pull-center\" data-placement=\"left\" data-align=\"top\" data-autoclose=\"true\"><input type=\"text\" id=\"" + la.get(i).getUsuarioBean().getDni() + la.get(i).getDia() + "1\" class=\"form-control\" value=\"" + la.get(i).getIniDia() + "\"><span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-time\"></span></span></div></td>");
                    out.println("<td class=\"td-small\"><div class=\"input-group clockpicker pull-center\" data-placement=\"left\" data-align=\"top\" data-autoclose=\"true\"><input type=\"text\" id=\"" + la.get(i).getUsuarioBean().getDni() + la.get(i).getDia() + "2\" class=\"form-control\" value=\"" + la.get(i).getIniBreak() + "\"><span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-time\"></span></span></div></td>");
                    out.println("<td class=\"td-small\"><div class=\"input-group clockpicker pull-center\" data-placement=\"left\" data-align=\"top\" data-autoclose=\"true\"><input type=\"text\" id=\"" + la.get(i).getUsuarioBean().getDni() + la.get(i).getDia() + "3\" class=\"form-control\" value=\"" + la.get(i).getFinBreak() + "\"><span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-time\"></span></span></div></td>");
                    out.println("<td class=\"td-small\"><div class=\"input-group clockpicker pull-center\" data-placement=\"left\" data-align=\"top\" data-autoclose=\"true\"><input type=\"text\" id=\"" + la.get(i).getUsuarioBean().getDni() + la.get(i).getDia() + "4\" class=\"form-control\" value=\"" + la.get(i).getFinDia() + "\"><span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-time\"></span></span></div></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<script src=\"resources/js/mantenimiento/modificaAsistencia.js\" type=\"text/javascript\"></script>");
                out.close();
            } catch (Exception e) {
                logger.error(e.toString().replace("\'", ""), e);
            }
        } else if (accion.equals("saveCha")) {
            try {
                String dnis = request.getParameter("ids");
                String vals = request.getParameter("vals");
                MantenimientoService mantenimientoService = UtilApp.getContext().getBean(MantenimientoService.class);
                mantenimientoService.saveAsistencia(dnis, vals);
                UsuarioBean usuarioBean = (UsuarioBean) request.getSession().getAttribute("usuario");
                logger.info(usuarioBean.getDni() + " esta actualizando " + dnis + " " + vals);
            } catch (Exception e) {
                logger.error(e.toString().replace("\'", ""), e);
            }
        }
    }
}
