package net.bpogroup.horario.dao.imp;

import net.bpogroup.horario.dao.AreaDAO;
import net.bpogroup.horario.dao.bean.AreaBean;
import net.bpogroup.horario.util.ConexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christianl3aron on 14/03/2015.
 */
public class AreaDAOImp implements AreaDAO {
    @Override
    public List<AreaBean> getAreas() throws Exception {

        List<AreaBean> listaAreas = new ArrayList<AreaBean>();
        Connection cn = ConexionBD.getInstance().obtenerConexion();
        StringBuilder sb = new StringBuilder();

        // Query
        sb.append("SELECT a.idarea, a.area ");
        sb.append("FROM bh_area a;");

        Statement st =cn.createStatement();
        ResultSet rs = st.executeQuery(sb.toString());

        while(rs.next()) {
            AreaBean areaBean = new AreaBean(rs.getInt("idarea"),rs.getString("area"));
            listaAreas.add(areaBean);
        }

        rs.close();
        cn.close();

        return listaAreas;
    }
}
