package net.bpogroup.horario.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import net.bpogroup.horario.dao.ReportDAO;
import net.bpogroup.horario.util.ConexionBD;


/**
 * Created by Christianl3aron on 12/03/2015.
 */

public class ReportDAOImp implements ReportDAO {

    public ReportDAOImp() {
    }

    public ResultSet obtenerReporte(Date ti, Date tf) throws Exception {

        StringBuilder cadena = new StringBuilder();
        cadena.append("SELECT concat(u.nombres,' ',u.apellidos), h.dni, a.area , ca.cargo, DATE_FORMAT(h.ini_jornada,'%Y-%m-%d'), DATE_FORMAT(h.ini_jornada,'%H:%i:%s') ,DATE_FORMAT( h.fin_jornada,'%Y-%m-%d'), DATE_FORMAT( h.fin_jornada,'%H:%i:%s') , h.ini_break, h.fin_break,time_format(subtime(timediff(h.fin_jornada,h.ini_jornada),timediff(h.fin_break,h.ini_break)),'%T'), co.condicion FROM bh_horario h INNER JOIN bh_usuario u ON u.dni = h.dni INNER JOIN bh_area a ON a.idarea = u.idarea INNER JOIN bh_cargo ca ON ca.idcargo = u.idcargo INNER JOIN bh_condicion co ON co.idcondicion = u.condicion WHERE  u.flag='A' and h.ini_jornada>? and h.ini_jornada<? order BY h.ini_jornada");
        Connection cn = ConexionBD.getInstance().obtenerConexion();
        PreparedStatement ps = cn.prepareStatement(cadena.toString());
        java.sql.Date sql = new java.sql.Date(ti.getTime());
        ps.setDate(1, sql);
        java.sql.Date sql2 = new java.sql.Date(tf.getTime());
        ps.setDate(2, sql2);
        ResultSet rs = ps.executeQuery();
        if(rs != null) {
            return rs;
        } else {
            ps.close();
            cn.close();
            return null;
        }
    }
}

