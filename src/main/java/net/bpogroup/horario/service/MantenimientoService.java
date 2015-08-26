package net.bpogroup.horario.service;

import net.bpogroup.horario.dao.bean.AreaBean;
import net.bpogroup.horario.dao.bean.AsistenciaBean;
import net.bpogroup.horario.dao.bean.UsuarioBean;

import java.util.Date;
import java.util.List;

/**
 * Created by Christianl3aron on 14/03/2015.
 */
public interface MantenimientoService {

    List<UsuarioBean> getUsuariosParaCombobox() throws Exception;

    List<AreaBean> getAreaParaCombobox() throws Exception;

    List<AsistenciaBean> getAsistenciasPorUsuarios(Date ti, Date tf, String listaDni) throws Exception;

    void saveAsistencia(String dnis, String vals) throws Exception;

}
