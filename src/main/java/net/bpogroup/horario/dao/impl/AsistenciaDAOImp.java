package net.bpogroup.horario.dao.impl;

import net.bpogroup.horario.dao.AsistenciaDAO;
import net.bpogroup.horario.model.AsistenciaBean;
import net.bpogroup.horario.util.AliasToBeanNestedResultTransformer;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Christianl3aron on 14/03/2015.
 */
@Repository
public class AsistenciaDAOImp implements AsistenciaDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
            for (String aStringList : stringList) {
                or.add((Restrictions.eq("u.dni", aStringList)));
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

                query.executeUpdate();
            }
            session.close();
        }
    }
}
