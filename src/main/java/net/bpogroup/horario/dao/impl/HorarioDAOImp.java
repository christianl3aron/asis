package net.bpogroup.horario.dao.impl;

import java.sql.Timestamp;
import java.util.Date;

import net.bpogroup.horario.dao.HorarioDAO;
import net.bpogroup.horario.model.AsistenciaBean;
import net.bpogroup.horario.model.UsuarioBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
@Repository
public class HorarioDAOImp implements HorarioDAO {


    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void iniciarJornada(String dni) {

        Session session = this.sessionFactory.openSession();
        Date ex3 = new Date();
        java.sql.Date ex2 = new java.sql.Date(ex3.getTime());
        Timestamp sq = new Timestamp(ex3.getTime());

        Query query = session.createQuery("update UsuarioBean c set c.estadoBean.codigo = :estado, c.ult_inicio=:date where c.dni = :dni");
        query.setParameter("estado", 1)
                .setParameter("date", sq)
                .setParameter("dni", dni);
        query.executeUpdate();

        AsistenciaBean asistenciaBean = new AsistenciaBean(new UsuarioBean(dni), ex2.toString(), sq.toString());

        try {
            session.beginTransaction();
            session.save(asistenciaBean);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public void iniciarAlmuerzo(String dni) {

        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("update UsuarioBean c set c.estadoBean.codigo = :estado where c.dni = :dni");
        query.setParameter("estado", 2)
                .setParameter("dni", dni);
        query.executeUpdate();

        Query query2 = session.createSQLQuery("UPDATE bh_horario SET ini_break  = :date WHERE dni= :dni AND ini_break IS NULL ORDER BY dia DESC LIMIT 1");
        query2.setParameter("date", new Timestamp((new Date()).getTime()))
                .setParameter("dni", dni);
        query2.executeUpdate();

        session.close();
    }

    public void finAlmuerzo(String dni) {

        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("update UsuarioBean c set c.estadoBean.codigo = :estado where c.dni = :dni");
        query.setParameter("estado", 3)
                .setParameter("dni", dni);
        query.executeUpdate();

        Query query2 = session.createSQLQuery("UPDATE bh_horario SET fin_break = :date WHERE dni= :dni AND fin_break IS NULL ORDER BY dia DESC LIMIT 1");
        query2.setParameter("date", new Timestamp((new Date()).getTime()))
                .setParameter("dni", dni);
        query2.executeUpdate();

        session.close();
    }

    public void finJornada(String dni) {

        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("update UsuarioBean c set c.estadoBean.codigo = :estado, c.ult_fin=:date  where c.dni = :dni");
        query.setParameter("estado", 4)
                .setParameter("date", new Timestamp((new Date()).getTime()))
                .setParameter("dni", dni);
        query.executeUpdate();

        Query query2 = session.createSQLQuery("UPDATE bh_horario SET fin_jornada = :date WHERE dni= :dni AND fin_jornada IS NULL ORDER BY dia DESC LIMIT 1");
        query2.setParameter("date", new Timestamp((new Date()).getTime()))
                .setParameter("dni", dni);
        query2.executeUpdate();

        session.close();
    }
}
