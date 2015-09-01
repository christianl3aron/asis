package net.bpogroup.horario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Christianl3aron on 30/08/2015.
 */
@Controller
public class ClockController {

    @RequestMapping(value = "/clock", method = RequestMethod.GET)
    public void sendClock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String sol = request.getParameter("sol");
        if (null != sol && sol.equals("fecha")) {
            // Retornar hora y fecha del sistema
            Calendar now = Calendar.getInstance();
            String[] strDays = new String[]{"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
            String[] strMonths = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            StringBuilder sb = new StringBuilder();
            sb.append(strDays[now.get(7) - 1]).append(", ").append(now.get(5)).append(" de ").append(strMonths[now.get(2)]).append(" del ").append(now.get(1)).append("-").append(dateFormat.format(date));
            out.write(sb.toString());
        }
    }
}
