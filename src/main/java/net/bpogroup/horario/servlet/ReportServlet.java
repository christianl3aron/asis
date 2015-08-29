package net.bpogroup.horario.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.service.imp.ReportServiceImp;
import net.bpogroup.horario.util.UtilApp;
import org.apache.log4j.Logger;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
public class ReportServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Envia el nombre del reporte a traves de AJAX

        Logger logger = Logger.getLogger(this.getClass());
        PrintWriter out = response.getWriter();
        String a = request.getParameter("a");
        StringBuilder sb = new StringBuilder();

        try {
            Date ti = UtilApp.getDate(request.getParameter("ti"));
            Date tf= UtilApp.addADay(request.getParameter("tf"));

            if(a.equals("rp")) {
                ReportServiceImp reportService = new ReportServiceImp();
                sb.append(reportService.obtenerReporte(ti, tf));

                // Recuperando usuario para generar log
                HttpSession miSesion = request.getSession();
                UsuarioBean usuarioBean = (UsuarioBean)miSesion.getAttribute("usuario");
                logger.info("Reporte generado por: " + usuarioBean.getDni() + "  " + new Date());
            }
        } catch (Exception var14) {
            logger.error(var14.toString().replace("\'", ""), var14);
        }

        // Enviado por AJAX
        out.write(sb.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

}
