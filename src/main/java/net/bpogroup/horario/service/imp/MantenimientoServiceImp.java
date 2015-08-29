package net.bpogroup.horario.service.imp;

import net.bpogroup.horario.dao.AreaDAO;
import net.bpogroup.horario.dao.AsistenciaDAO;
import net.bpogroup.horario.dao.UsuarioDAO;
import net.bpogroup.horario.model.AreaBean;
import net.bpogroup.horario.model.AsistenciaBean;
import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.dao.impl.AreaDAOImp;
import net.bpogroup.horario.dao.impl.AsistenciaDAOImp;
import net.bpogroup.horario.dao.impl.UsuarioDAOImp;
import net.bpogroup.horario.service.MantenimientoService;
import net.bpogroup.horario.util.UtilApp;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Christianl3aron on 14/03/2015.
 */
public class MantenimientoServiceImp implements MantenimientoService {



    public List<UsuarioBean> getUsuariosParaCombobox() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        UsuarioDAO usuarioDAO = context.getBean(UsuarioDAO.class);
        return usuarioDAO.listAll();
    }

    public List<AreaBean> getAreaParaCombobox() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        AreaDAO areaDAO = context.getBean(AreaDAO.class);
        return areaDAO.listAll();
    }

    public List<AsistenciaBean> getAsistenciasPorUsuarios(Date ti, Date tf, String listaDni) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        AsistenciaDAO asistenciaDAO = context.getBean(AsistenciaDAO.class);
        List<AsistenciaBean> list = asistenciaDAO.listAsistenciasPorUsuarios(ti, tf, listaDni);
        return UtilApp.formatDateInAsistenciaBeanList(list, 11, 16);
    }

    public void saveAsistencia(String dnis, String vals) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        AsistenciaDAO asistenciaDAO = context.getBean(AsistenciaDAO.class);
        asistenciaDAO.update(dnis, vals);
    }
}
