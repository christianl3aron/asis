package net.bpogroup.horario.service.imp;

import net.bpogroup.horario.dao.AreaDAO;
import net.bpogroup.horario.dao.AsistenciaDAO;
import net.bpogroup.horario.dao.UsuarioDAO;
import net.bpogroup.horario.dao.bean.AreaBean;
import net.bpogroup.horario.dao.bean.AsistenciaBean;
import net.bpogroup.horario.dao.bean.UsuarioBean;
import net.bpogroup.horario.dao.imp.AreaDAOImp;
import net.bpogroup.horario.dao.imp.AsistenciaDAOImp;
import net.bpogroup.horario.dao.imp.UsuarioDAOImp;
import net.bpogroup.horario.service.MantenimientoService;

import java.util.Date;
import java.util.List;

/**
 * Created by Christianl3aron on 14/03/2015.
 */
public class MantenimientoServiceImp implements MantenimientoService {

    @Override
    public List<UsuarioBean> getUsuariosParaCombobox() throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAOImp();
        return usuarioDAO.getUsuarios();
    }

    @Override
    public List<AreaBean> getAreaParaCombobox() throws Exception {
        AreaDAO areaDAO = new AreaDAOImp();
        return areaDAO.getAreas();
    }

    @Override
    public List<AsistenciaBean> getAsistenciasPorUsuarios(Date ti, Date tf, String listaDni) throws Exception {
        AsistenciaDAO asistenciaDAO = new AsistenciaDAOImp();
        return asistenciaDAO.getAsistenciasPorUsuarios(ti, tf, listaDni);
    }

    @Override
    public void saveAsistencia(String dnis, String vals) throws Exception {
        AsistenciaDAO asistenciaDAO = new AsistenciaDAOImp();
        asistenciaDAO.saveAsistencia(dnis, vals);
    }
}
