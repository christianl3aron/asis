package net.bpogroup.horario.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
public class ConexionBD {
    static ConexionBD instancia;

    private ConexionBD() {
    }

    public static ConexionBD getInstance() {
        if(instancia == null) {
            instancia = new ConexionBD();
        }

        return instancia;
    }

    public Connection obtenerConexion() throws Exception {
        Connection cn = null;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        //cn = DriverManager.getConnection("jdbc:mysql://192.168.50.11:3306/bpohorario", "root", "bpogroup1");
        cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bpohorario", "root", "panconpan3");
        //cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bpohorario", "root", "root");

        return cn;
    }
}