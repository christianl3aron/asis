package net.bpogroup.horario.servlet;

import net.bpogroup.horario.dao.AsistenciaDAO;
import net.bpogroup.horario.model.AreaBean;
import net.bpogroup.horario.model.AsistenciaBean;
import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.service.MantenimientoService;
import net.bpogroup.horario.service.MonitorService;
import net.bpogroup.horario.service.imp.MantenimientoServiceImp;
import net.bpogroup.horario.util.UtilApp;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by Christianl3aron on 14/03/2015.
 */
public class MonitorServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        Logger logger = Logger.getLogger(this.getClass());
        String action = request.getParameter("a");

        if (action.equals("ma")) {
            // Si la accion es ma("Monitorear aisstencia") enviar List<AsistenaciaBean>
            try {
                MonitorService monitorService = context.getBean(MonitorService.class);
                List<AsistenciaBean> list = monitorService.listAsistenciasParaMonitor();
                request.setAttribute("listaAsistencia", list);

                RequestDispatcher dispa = getServletContext().getRequestDispatcher("/monitorAsistencia.jsp");
                dispa.forward(request, response);
            } catch (Exception e) {
                logger.error(e.toString().replace("\'", ""), e);
            }

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }
}