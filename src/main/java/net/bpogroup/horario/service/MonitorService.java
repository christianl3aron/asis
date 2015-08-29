package net.bpogroup.horario.service;

import net.bpogroup.horario.model.AsistenciaBean;

import java.util.List;

/**
 * Created by Christianl3aron on 29/08/2015.
 */
public interface MonitorService {

    List<AsistenciaBean> listAsistenciasParaMonitor() throws Exception;

}
