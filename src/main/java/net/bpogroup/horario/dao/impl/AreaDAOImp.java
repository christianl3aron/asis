package net.bpogroup.horario.dao.impl;

import net.bpogroup.horario.dao.AreaDAO;
import net.bpogroup.horario.model.AreaBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Christianl3aron on 14/03/2015.
 */
@Repository
public class AreaDAOImp implements AreaDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<AreaBean> listAll() throws Exception {
        Session session = this.sessionFactory.openSession();
        List<AreaBean> areaBeanList = session.createQuery("from AreaBean ").list();
        session.close();
        return areaBeanList;
    }
}
