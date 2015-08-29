package net.bpogroup.horario.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.bpogroup.horario.dao.*;
import net.bpogroup.horario.model.AreaBean;
import net.bpogroup.horario.model.EstadoBean;
import net.bpogroup.horario.model.TipoUsuarioBean;
import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.util.AliasToBeanNestedResultTransformer;
import net.bpogroup.horario.util.ConexionBD;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
public class UsuarioDAOImp implements UsuarioDAO {


    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UsuarioBean getUsuario(String dni) throws Exception {
        // Obtener usuario de bh_usuario para validarlo en el login

        UsuarioBean usuarioBean = null;
        Connection cn = ConexionBD.getInstance().obtenerConexion();
        StringBuilder sb = new StringBuilder();

        // Query
        sb.append("SELECT u.nombres, u.apellidos, t.idtipo_usuario, t.denominacion, e.idestado, e.estado, u.ult_inicio, u.ult_fin FROM bh_usuario u ");
        sb.append("INNER JOIN bh_tipo_usuario t ON t.idtipo_usuario=u.idtipo_usuario ");
        sb.append("INNER JOIN bh_estado e ON e.idestado = u.idestado ");
        sb.append("WHERE u.dni=?");

        PreparedStatement st = cn.prepareStatement(sb.toString());
        st.setString(1, dni);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            usuarioBean = new UsuarioBean(dni, rs.getString("nombres"), rs.getString("apellidos"), new TipoUsuarioBean(rs.getInt("idtipo_usuario"), rs.getString("denominacion")), new EstadoBean(rs.getInt("idestado"), rs.getString("estado")), rs.getTimestamp("ult_inicio"), rs.getTimestamp("ult_fin"));
        }

        rs.close();
        cn.close();

        return usuarioBean;
    }

    public List<UsuarioBean> getUsuarios() throws Exception {
        // Obtener lista usuarios de bh_usuario para mostrarlos en el chosen multiselect

        List<UsuarioBean> listaUsuarios = new ArrayList<UsuarioBean>();
        Connection cn = ConexionBD.getInstance().obtenerConexion();
        StringBuilder sb = new StringBuilder();

        // Query
        sb.append("SELECT u.dni, u.nombres, u.apellidos, u.idarea ");
        sb.append("FROM bh_usuario u ");
        sb.append("WHERE u.flag='A'");

        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sb.toString());

        while (rs.next()) {
            UsuarioBean usuarioBean = new UsuarioBean(rs.getString("dni"), rs.getString("nombres"), rs.getString("apellidos"), new AreaBean(rs.getInt("idarea")));
            listaUsuarios.add(usuarioBean);
        }

        rs.close();
        cn.close();

        return listaUsuarios;
    }

    public void save(UsuarioBean usuarioBean) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(usuarioBean);
        transaction.commit();
        session.close();
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