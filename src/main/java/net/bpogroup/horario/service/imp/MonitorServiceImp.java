package net.bpogroup.horario.service.imp;

import net.bpogroup.horario.dao.AsistenciaDAO;
import net.bpogroup.horario.model.AsistenciaBean;
import net.bpogroup.horario.service.MonitorService;
import net.bpogroup.horario.util.UtilApp;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Christianl3aron on 29/08/2015.
 */
public class MonitorServiceImp implements MonitorService {


    public List<AsistenciaBean> listAsistenciasParaMonitor() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        AsistenciaDAO asistenciaDAO = context.getBean(AsistenciaDAO.class);
        List<AsistenciaBean> list = asistenciaDAO.listAsistenciasParaMonitor();
        return UtilApp.formatDateInAsistenciaBeanList(list, 11, 19);
    }
}
