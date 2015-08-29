package net.bpogroup.horario.service.imp;

import net.bpogroup.horario.dao.HorarioDAO;
import net.bpogroup.horario.dao.UsuarioDAO;
import net.bpogroup.horario.dao.impl.HorarioDAOImp;
import net.bpogroup.horario.service.HorarioService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Christianl3aron on 12/03/2015.
 */


public class HorarioServiceImp implements HorarioService {

    public void iniciarJornada(String dni) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        HorarioDAO horarioDAO = context.getBean(HorarioDAO.class);
        horarioDAO.iniciarJornada(dni);
    }

    public void iniciarAlmuerzo(String dni) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        HorarioDAO horarioDAO = context.getBean(HorarioDAO.class);
        horarioDAO.iniciarAlmuerzo(dni);
    }

    public void finAlmuerzo(String dni) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        HorarioDAO horarioDAO = context.getBean(HorarioDAO.class);
        horarioDAO.finAlmuerzo(dni);
    }

    public void finJornada(String dni) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        HorarioDAO horarioDAO = context.getBean(HorarioDAO.class);
        horarioDAO.finJornada(dni);
    }
}

