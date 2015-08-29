package net.bpogroup.horario.dao.impl;

import net.bpogroup.horario.dao.AreaDAO;
import net.bpogroup.horario.model.AreaBean;
import net.bpogroup.horario.util.ConexionBD;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christianl3aron on 14/03/2015.
 */
public class AreaDAOImp implements AreaDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<AreaBean> getAreas() throws Exception {

        List<AreaBean> listaAreas = new ArrayList<AreaBean>();
        Connection cn = ConexionBD.getInstance().obtenerConexion();
        StringBuilder sb = new StringBuilder();

        // Query
        sb.append("SELECT a.idarea, a.area ");
        sb.append("FROM bh_area a;");

        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sb.toString());

        while (rs.next()) {
            AreaBean areaBean = new AreaBean(rs.getInt("idarea"), rs.getString("area"));
            listaAreas.add(areaBean);
        }

        rs.close();
        cn.close();

        return listaAreas;
    }

    public void save(AreaBean areaBean) throws Exception {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(areaBean);
        transaction.commit();
        session.close();
    }

    public List<AreaBean> listAll() throws Exception {
        Session session = this.sessionFactory.openSession();
        List<AreaBean> areaBeanList = session.createQuery("from AreaBean ").list();
        session.close();
        return areaBeanList;
    }
}
