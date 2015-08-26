package net.bpogroup.horario.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.bpogroup.horario.dao.*;
import net.bpogroup.horario.dao.bean.AreaBean;
import net.bpogroup.horario.dao.bean.EstadoBean;
import net.bpogroup.horario.dao.bean.TipoUsuarioBean;
import net.bpogroup.horario.dao.bean.UsuarioBean;
import net.bpogroup.horario.util.ConexionBD;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
public class UsuarioDAOImp implements UsuarioDAO {

    public UsuarioDAOImp() {
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

        if(rs.next()) {
            usuarioBean = new UsuarioBean(dni, rs.getString("nombres"), rs.getString("apellidos"), new TipoUsuarioBean(rs.getInt("idtipo_usuario"), rs.getString("denominacion")), new EstadoBean(rs.getInt("idestado"), rs.getString("estado")), rs.getTimestamp("ult_inicio"), rs.getTimestamp("ult_fin"));
        }

        rs.close();
        cn.close();

        return usuarioBean;
    }

    @Override
    public List<UsuarioBean> getUsuarios() throws Exception {
        // Obtener lista usuarios de bh_usuario para mostrarlos en el chosen multiselect

        List<UsuarioBean> listaUsuarios = new ArrayList<UsuarioBean>();
        Connection cn = ConexionBD.getInstance().obtenerConexion();
        StringBuilder sb = new StringBuilder();

        // Query
        sb.append("SELECT u.dni, u.nombres, u.apellidos, u.idarea ");
        sb.append("FROM bh_usuario u ");
        sb.append("WHERE u.flag='A'");

        Statement st =cn.createStatement();
        ResultSet rs = st.executeQuery(sb.toString());

        while(rs.next()) {
            UsuarioBean usuarioBean = new UsuarioBean(rs.getString("dni"), rs.getString("nombres"), rs.getString("apellidos"), new AreaBean(rs.getInt("idarea")));
            listaUsuarios.add(usuarioBean);
        }

        rs.close();
        cn.close();

        return listaUsuarios;
    }
}