package net.bpogroup.horario.service.imp;

import net.bpogroup.horario.dao.AreaDAO;
import net.bpogroup.horario.dao.AsistenciaDAO;
import net.bpogroup.horario.dao.UsuarioDAO;
import net.bpogroup.horario.model.AreaBean;
import net.bpogroup.horario.model.AsistenciaBean;
import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.service.MantenimientoService;
import net.bpogroup.horario.util.UtilApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Christianl3aron on 14/03/2015.
 */
@Service
public class MantenimientoServiceImp implements MantenimientoService {

    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    private AreaDAO areaDAO;
    @Autowired
    private AsistenciaDAO asistenciaDAO;

    public List<UsuarioBean> getUsuariosParaCombobox() throws Exception {
        return usuarioDAO.listAll();
    }

    public List<AreaBean> getAreaParaCombobox() throws Exception {
        return areaDAO.listAll();
    }

    public List<AsistenciaBean> getAsistenciasPorUsuarios(Date ti, Date tf, String listaDni) throws Exception {
        List<AsistenciaBean> list = asistenciaDAO.listAsistenciasPorUsuarios(ti, tf, listaDni);
        return UtilApp.formatDateInAsistenciaBeanList(list, 11, 16);
    }

    public void saveAsistencia(String dnis, String vals) throws Exception {
        asistenciaDAO.update(dnis, vals);
    }


}
