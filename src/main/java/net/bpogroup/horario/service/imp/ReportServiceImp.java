package net.bpogroup.horario.service.imp;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Date;

import net.bpogroup.horario.dao.ReportDAO;
import net.bpogroup.horario.service.ReportService;
import net.bpogroup.horario.util.UtilApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
@Service
public class ReportServiceImp implements ReportService {

    @Autowired
    private ReportDAO reportDAO;

    public String obtenerReporte(Date ti, Date tf) throws Exception {

        StringBuilder archivo = new StringBuilder("resources/temp/Reporte-").append(UtilApp.getDate4Report()).append(".xls");
        ResultSet rs = reportDAO.obtenerReporte(ti, tf);
        //PrintWriter writer = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("/usr/local/tomcat7/webapps/bpogroup_control/" + archivo)), "UTF-8"));
        //PrintWriter writer = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("E:\\Workspace\\bpogroup_control\\web\\" + archivo.toString())), "UTF-8"));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("C:\\Users\\Christianl3aron\\Workspace\\asis\\src\\main\\webapp\\" + archivo)), "UTF-8"));
        writer.append("Nro\tApellidos y Nombres\tDNI\tArea\tCargo\tFecha de Ingreso\tHora de Ingreso\tFecha de Salida\tHora de Salida\tHorario de Inicio de Refrigerio\tHorario de Termino de Refrigerio\tGestion por dia\tEstado").println();

        for (int i = 1; rs.next(); ++i) {
            writer.append(String.valueOf(i)).append("\t").append(rs.getString(1)).append("\t").append(rs.getString(2)).append("\t").append(rs.getString(3)).append("\t").append(rs.getString(4)).append("\t").append(rs.getString(5)).append("\t").append(rs.getString(6)).append("\t").append(rs.getString(7)).append("\t").append(rs.getString(8)).append("\t").append(rs.getString(9)).append("\t").append(rs.getString(10)).append("\t").append(rs.getString(11)).append("\t").append(rs.getString(12)).println();
        }

        writer.close();
        return archivo.toString();
    }

}