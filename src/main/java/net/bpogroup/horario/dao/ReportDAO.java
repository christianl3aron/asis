package net.bpogroup.horario.dao;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
import java.sql.ResultSet;
import java.util.Date;

public interface ReportDAO {
    ResultSet obtenerReporte(Date var1, Date var2) throws Exception;
}
