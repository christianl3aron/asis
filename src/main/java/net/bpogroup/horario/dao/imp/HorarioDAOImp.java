package net.bpogroup.horario.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import net.bpogroup.horario.dao.HorarioDAO;
import net.bpogroup.horario.util.ConexionBD;
import org.apache.log4j.Logger;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
public class HorarioDAOImp implements HorarioDAO {

    public HorarioDAOImp() {
    }

    public void iniciarJornada(String dni) {

        Logger logger = Logger.getLogger(this.getClass());
        Connection cn = null;

        try {
            // Creacion de conexion a BD
            cn = ConexionBD.getInstance().obtenerConexion();
            cn.setAutoCommit(false);

            // Generacion de fecha y hora
            Date ex3 = new Date();
            java.sql.Date ex2 = new java.sql.Date(ex3.getTime());
            Timestamp sq = new Timestamp(ex3.getTime());

            // Insercion a las tablas bh_usuario y bh_horario
            PreparedStatement ps = cn.prepareStatement("UPDATE bh_usuario SET idestado=1,ult_inicio=? where dni=?");
            ps.setTimestamp(1, sq);
            ps.setString(2, dni);
            ps.execute();
            PreparedStatement ps2 = cn.prepareStatement("INSERT INTO bh_horario(dni,ini_jornada,dia) VALUES(?,?,?)");
            ps2.setString(1, dni);
            ps2.setTimestamp(2, sq);
            ps2.setDate(3, ex2);
            ps2.execute();
            cn.commit();
            cn.close();

        } catch (Exception var19) {
            logger.error(var19.toString().replace("\'", ""), var19);

            try {
                if(cn != null) {
                    cn.rollback();
                }
            } catch (SQLException var18) {
                logger.error(var18.toString().replace("\'", ""), var18);
            }
        } finally {
            try {
                if(cn != null) {
                    cn.close();
                }
            } catch (SQLException var17) {
                logger.error(var17.toString().replace("\'", ""), var17);
            }

        }

    }

    public void iniciarAlmuerzo(String dni) {
        Logger logger = Logger.getLogger(this.getClass());
        Connection cn = null;

        try {
            // Creacion de conexion a la BD
            cn = ConexionBD.getInstance().obtenerConexion();
            cn.setAutoCommit(false);

            // Actualizando registro en las tablas bh_usuario y bh_horario
            PreparedStatement ex3 = cn.prepareStatement("UPDATE bh_usuario SET idestado=2 where dni=?");
            ex3.setString(1, dni);
            ex3.execute();
            PreparedStatement ex2 = cn.prepareStatement("UPDATE bh_horario SET ini_break=? WHERE dni=? AND ini_break IS NULL ORDER BY dia DESC LIMIT 1");
            Timestamp sq = new Timestamp((new Date()).getTime());
            ex2.setTimestamp(1, sq);
            ex2.setString(2, dni);
            ex2.execute();
            cn.commit();
            cn.close();

        } catch (Exception var17) {
            logger.error(var17.toString().replace("\'", ""), var17);

            try {
                if(cn != null) {
                    cn.rollback();
                }
            } catch (SQLException var16) {
                logger.error(var16.toString().replace("\'", ""), var16);
            }
        } finally {
            try {
                if(cn != null) {
                    cn.close();
                }
            } catch (SQLException var15) {
                logger.error(var15.toString().replace("\'", ""), var15);
            }

        }

    }

    public void finAlmuerzo(String dni) {
        Logger logger = Logger.getLogger(this.getClass());
        Connection cn = null;

        try {
            // Creacion de conexion a la BD
            cn = ConexionBD.getInstance().obtenerConexion();
            cn.setAutoCommit(false);

            // Actualizando registro en las tablas bh_usuario y bh_horario
            PreparedStatement ex3 = cn.prepareStatement("UPDATE bh_usuario SET idestado=3 where dni=?");
            ex3.setString(1, dni);
            ex3.execute();
            PreparedStatement ex2 = cn.prepareStatement("UPDATE bh_horario SET fin_break=? WHERE dni=? AND fin_break IS NULL ORDER BY dia DESC LIMIT 1");
            Timestamp sq = new Timestamp((new Date()).getTime());
            ex2.setTimestamp(1, sq);
            ex2.setString(2, dni);
            ex2.execute();
            cn.commit();
            cn.close();

        } catch (Exception var17) {
            logger.error(var17.toString().replace("\'", ""), var17);

            try {
                if(cn != null) {
                    cn.rollback();
                }
            } catch (SQLException var16) {
                logger.error(var16.toString().replace("\'", ""), var16);
            }
        } finally {
            try {
                if(cn != null) {
                    cn.close();
                }
            } catch (SQLException var15) {
                logger.error(var15.toString().replace("\'", ""), var15);
            }

        }

    }

    public void finJornada(String dni) {

        Logger logger = Logger.getLogger(this.getClass());
        Connection cn = null;

        try {
            // Creacion de conexion a la BD
            cn = ConexionBD.getInstance().obtenerConexion();
            cn.setAutoCommit(false);

            // Actualizando registro en las tablas bh_usuario y bh_horario
            Date ex3 = new Date();
            Timestamp ex2 = new Timestamp(ex3.getTime());
            PreparedStatement ps = cn.prepareStatement("UPDATE bh_usuario SET idestado=4, ult_fin=? where dni=?");
            ps.setTimestamp(1, ex2);
            ps.setString(2, dni);
            ps.execute();
            PreparedStatement ps2 = cn.prepareStatement("UPDATE bh_horario SET fin_jornada=? WHERE dni=? AND fin_jornada IS NULL ORDER BY dia DESC LIMIT 1");
            ps2.setTimestamp(1, ex2);
            ps2.setString(2, dni);
            ps2.execute();
            cn.commit();
            cn.close();
        } catch (Exception var18) {
            logger.error(var18.toString().replace("\'", ""), var18);

            try {
                if(cn != null) {
                    cn.rollback();
                }
            } catch (SQLException var17) {
                logger.error(var17.toString().replace("\'", ""), var17);
            }
        } finally {
            try {
                if(cn != null) {
                    cn.close();
                }
            } catch (SQLException var16) {
                logger.error(var16.toString().replace("\'", ""), var16);
            }

        }

    }
}
