package net.bpogroup.horario.dao.impl;

import net.bpogroup.horario.dao.AsistenciaDAO;
import net.bpogroup.horario.model.AsistenciaBean;
import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.util.AliasToBeanNestedResultTransformer;
import net.bpogroup.horario.util.ConexionBD;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Christianl3aron on 14/03/2015.
 */
public class AsistenciaDAOImp implements AsistenciaDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<AsistenciaBean> getAsistenciasPorUsuarios(Date ti, Date tf, String listaDni) throws Exception {

        List<AsistenciaBean> listaAsistencias = new ArrayList<AsistenciaBean>();
        List<String> lista = Arrays.asList(listaDni.split("\\s*,\\s*"));

        if (lista.size() > 0) {
            StringBuilder cadena = new StringBuilder();
            cadena.append("SELECT u.nombres, u.apellidos, h.dni, DATE_FORMAT(h.ini_jornada,'%Y-%m-%d') as dia, ");
            cadena.append("DATE_FORMAT(h.ini_jornada,'%H:%i') as inidia, DATE_FORMAT( h.fin_jornada,'%H:%i') as findia, ");
            cadena.append("DATE_FORMAT(h.ini_break,'%H:%i') as inibreak, DATE_FORMAT(h.fin_break,'%H:%i') as finbreak ");
            cadena.append("FROM bh_horario h INNER JOIN bh_usuario u ON u.dni = h.dni ");
            cadena.append("WHERE  u.flag='A' AND h.ini_jornada>? AND h.ini_jornada<? ");
            cadena.append("AND (");
            for (int i = 0; i < lista.size(); i++) {
                cadena.append("u.dni='").append(lista.get(i)).append("' ");
                if (i < lista.size() - 1) {
                    cadena.append("OR ");
                }
            }
            cadena.append(") ");
            cadena.append("ORDER BY h.ini_jornada ");

            Connection cn = ConexionBD.getInstance().obtenerConexion();
            PreparedStatement ps = cn.prepareStatement(cadena.toString());
            java.sql.Date sql = new java.sql.Date(ti.getTime());
            ps.setDate(1, sql);
            java.sql.Date sql2 = new java.sql.Date(tf.getTime());
            ps.setDate(2, sql2);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AsistenciaBean asistenciaBean = new AsistenciaBean(
                        new UsuarioBean(rs.getString("dni"), rs.getString("nombres"), rs.getString("apellidos")),
                        rs.getString("dia"),
                        rs.getString("inidia"),
                        rs.getString("inibreak"),
                        rs.getString("finbreak"),
                        rs.getString("findia"));
                listaAsistencias.add(asistenciaBean);
            }
        }
        return listaAsistencias;
    }

    public void saveAsistencia(String ids, String vals) {
        Logger logger = Logger.getLogger(this.getClass());
        if (ids.length() > 6 && vals.length() > 3) {
            List<String> lId = Arrays.asList(ids.split("\\s*,\\s*"));
            List<String> lVal = Arrays.asList(vals.split("\\s*,\\s*"));

            Connection cn = null;
            try {
                cn = ConexionBD.getInstance().obtenerConexion();
                cn.setAutoCommit(false);

                for (int i = 0; i < lId.size(); i++) {
                    PreparedStatement ps;
                    if (lId.get(i).substring(18).equals("1")) {
                        ps = cn.prepareStatement("UPDATE bh_horario SET ini_jornada=? WHERE dni=? AND dia=?");
                    } else if (lId.get(i).substring(18).equals("2")) {
                        ps = cn.prepareStatement("UPDATE bh_horario SET ini_break=? WHERE dni=? AND dia=?");
                    } else if (lId.get(i).substring(18).equals("3")) {
                        ps = cn.prepareStatement("UPDATE bh_horario SET fin_break=? WHERE dni=? AND dia=?");
                    } else {
                        ps = cn.prepareStatement("UPDATE bh_horario SET fin_jornada=? WHERE dni=? AND dia=?");
                    }
                    ps.setString(1, lId.get(i).substring(8, 18) + " " + lVal.get(i));//fecha + hora
                    ps.setString(2, lId.get(i).substring(0, 8));//dni
                    ps.setString(3, lId.get(i).substring(8, 18));//fecha
                    ps.execute();
                }
                cn.commit();
                cn.close();
            } catch (
                    Exception e
                    )

            {
                logger.error(e.toString().replace("\'", ""), e);
            }
        }

    }

    public List<AsistenciaBean> listAsistenciasPorUsuarios(Date ti, Date tf, String listaDni) throws Exception {


        List<AsistenciaBean> list = new ArrayList<AsistenciaBean>();
        List<String> stringList = Arrays.asList(listaDni.split("\\s*,\\s*"));

        if (stringList.size() > 0) {
            Session session = this.sessionFactory.openSession();
            Criteria criteria = session.createCriteria(AsistenciaBean.class);
            ProjectionList projectionList = Projections.projectionList();
            criteria.createAlias("usuarioBean", "u");
            projectionList.add(Projections.property("u.dni").as("usuarioBean.dni"))
                    .add(Projections.property("u.nombres").as("usuarioBean.nombres"))
                    .add(Projections.property("u.apellidos").as("usuarioBean.apellidos"))
                    .add(Projections.property("dia").as("dia"))
                    .add(Projections.property("iniDia").as("iniDia"))
                    .add(Projections.property("iniBreak").as("iniBreak"))
                    .add(Projections.property("finBreak").as("finBreak"))
                    .add(Projections.property("finDia").as("finDia"));

            Disjunction or = Restrictions.disjunction();

            for (int i = 0; i < stringList.size(); i++) {
                or.add((Restrictions.eq("u.dni", stringList.get(i))));
            }
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            criteria.add(or)
                    .add(Restrictions.ge("dia", formatter.format(ti)))
                    .add(Restrictions.lt("dia", formatter.format(tf)))
                    .addOrder(Order.asc("iniDia"));

            criteria.setProjection(projectionList)
                    .setResultTransformer(new AliasToBeanNestedResultTransformer(AsistenciaBean.class));

            list = (List<AsistenciaBean>) criteria.list();
            session.close();
        }

        return list;
    }

    public List<AsistenciaBean> listAsistenciasParaMonitor() throws Exception {

        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(AsistenciaBean.class);
        ProjectionList projectionList = Projections.projectionList();
        criteria.createAlias("usuarioBean", "u");
        projectionList.add(Projections.property("u.dni").as("usuarioBean.dni"))
                .add(Projections.property("u.nombres").as("usuarioBean.nombres"))
                .add(Projections.property("u.apellidos").as("usuarioBean.apellidos"))
                .add(Projections.property("dia").as("dia"))
                .add(Projections.property("iniDia").as("iniDia"))
                .add(Projections.property("iniBreak").as("iniBreak"))
                .add(Projections.property("finBreak").as("finBreak"))
                .add(Projections.property("finDia").as("finDia"));
        criteria.add(Restrictions.eq("dia", formatter.format(date)))
                .add(Restrictions.eq("u.monitor", "S"));

        criteria.setProjection(projectionList)
                .setResultTransformer(new AliasToBeanNestedResultTransformer(AsistenciaBean.class));

        List<AsistenciaBean> list = (List<AsistenciaBean>) criteria.list();
        session.close();
        return list;
    }

    public void update(String ids, String vals) throws Exception {
        if (ids.length() > 6 && vals.length() > 3) {
            List<String> lId = Arrays.asList(ids.split("\\s*,\\s*"));
            List<String> lVal = Arrays.asList(vals.split("\\s*,\\s*"));

            Session session = sessionFactory.openSession();
            Query query;

            for (int i = 0; i < lId.size(); i++) {
                PreparedStatement ps;
                if (lId.get(i).substring(18).equals("1")) {
                    query = session.createQuery("update AsistenciaBean c set c.iniDia = :date where c.usuarioBean.dni = :dni and c.dia = :dia");
                } else if (lId.get(i).substring(18).equals("2")) {
                    query = session.createQuery("update AsistenciaBean c set c.iniBreak = :date where c.usuarioBean.dni = :dni and c.dia = :dia");
                } else if (lId.get(i).substring(18).equals("3")) {
                    query = session.createQuery("update AsistenciaBean c set c.finBreak = :date where c.usuarioBean.dni = :dni and c.dia = :dia");
                } else {
                    query = session.createQuery("update AsistenciaBean c set c.finDia = :date where c.usuarioBean.dni = :dni and c.dia = :dia");
                }
                query.setParameter("date", lId.get(i).substring(8, 18) + " " + lVal.get(i))
                        .setParameter("dni", lId.get(i).substring(0, 8))
                        .setParameter("dia", lId.get(i).substring(8, 18));

                int result = query.executeUpdate();
            }
            session.close();
        }
    }
}
