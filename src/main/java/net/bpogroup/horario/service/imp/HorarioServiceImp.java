package net.bpogroup.horario.service.imp;

import net.bpogroup.horario.dao.HorarioDAO;
import net.bpogroup.horario.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Christianl3aron on 12/03/2015.
 */


@Service
public class HorarioServiceImp implements HorarioService {

    @Autowired
    private HorarioDAO horarioDAO;

    public void iniciarJornada(String dni) throws Exception {
        horarioDAO.iniciarJornada(dni);
    }

    public void iniciarAlmuerzo(String dni) throws Exception {
        horarioDAO.iniciarAlmuerzo(dni);
    }

    public void finAlmuerzo(String dni) throws Exception {
        horarioDAO.finAlmuerzo(dni);
    }

    public void finJornada(String dni) throws Exception {
        horarioDAO.finJornada(dni);
    }

}

