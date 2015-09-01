package net.bpogroup.horario.service.imp;

import net.bpogroup.horario.dao.AsistenciaDAO;
import net.bpogroup.horario.model.AsistenciaBean;
import net.bpogroup.horario.service.MonitorService;
import net.bpogroup.horario.util.UtilApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Christianl3aron on 29/08/2015.
 */
@Service
public class MonitorServiceImp implements MonitorService {

    @Autowired
    private AsistenciaDAO asistenciaDAO;

    public List<AsistenciaBean> listAsistenciasParaMonitor() throws Exception {

        List<AsistenciaBean> list = asistenciaDAO.listAsistenciasParaMonitor();
        return UtilApp.formatDateInAsistenciaBeanList(list, 11, 19);
    }

}
