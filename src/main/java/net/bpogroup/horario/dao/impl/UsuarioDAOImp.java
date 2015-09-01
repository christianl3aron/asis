package net.bpogroup.horario.dao.impl;

import java.util.List;

import net.bpogroup.horario.dao.*;
import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.util.AliasToBeanNestedResultTransformer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
@Repository
public class UsuarioDAOImp implements UsuarioDAO {


    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UsuarioBean listOne(String dni) throws Exception {
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UsuarioBean.class);
        ProjectionList projectionList = Projections.projectionList();
        criteria.createAlias("tipoUsuarioBean", "t");
        criteria.createAlias("estadoBean", "e");
        projectionList.add(Projections.property("dni").as("dni"));
        projectionList.add(Projections.property("nombres").as("nombres"));
        projectionList.add(Projections.property("apellidos").as("apellidos"));
        projectionList.add(Projections.property("t.codigo").as("tipoUsuarioBean.codigo"));
        projectionList.add(Projections.property("t.denominacion").as("tipoUsuarioBean.denominacion"));
        projectionList.add(Projections.property("e.codigo").as("estadoBean.codigo"));
        projectionList.add(Projections.property("e.estado").as("estadoBean.estado"));
        projectionList.add(Projections.property("ult_inicio").as("ult_inicio"));
        projectionList.add(Projections.property("ult_fin").as("ult_fin"));
        criteria.add(Restrictions.eq("dni", dni));

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(new AliasToBeanNestedResultTransformer(UsuarioBean.class));

        UsuarioBean usuarioBean = (UsuarioBean) criteria.uniqueResult();
        session.close();
        return usuarioBean;
    }

    public List<UsuarioBean> listAll() throws Exception {
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UsuarioBean.class);
        ProjectionList projectionList = Projections.projectionList();
        criteria.createAlias("areaBean", "a");
        projectionList.add(Projections.property("dni").as("dni"));
        projectionList.add(Projections.property("nombres").as("nombres"));
        projectionList.add(Projections.property("apellidos").as("apellidos"));
        projectionList.add(Projections.property("a.codigo").as("areaBean.codigo"));
        projectionList.add(Projections.property("a.denominacion").as("areaBean.denominacion"));
        criteria.add(Restrictions.sqlRestriction("flag='A'"));

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(new AliasToBeanNestedResultTransformer(UsuarioBean.class));

        List<UsuarioBean> lista = (List<UsuarioBean>) criteria.list();
        session.close();
        return lista;
    }
}