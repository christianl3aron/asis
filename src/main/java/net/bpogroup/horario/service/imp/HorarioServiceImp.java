package net.bpogroup.horario.service.imp;

import net.bpogroup.horario.dao.HorarioDAO;
import net.bpogroup.horario.dao.imp.HorarioDAOImp;
import net.bpogroup.horario.service.HorarioService;

/**
 * Created by Christianl3aron on 12/03/2015.
 */


public class HorarioServiceImp implements HorarioService {
    public HorarioServiceImp() {
    }

    public void iniciarJornada(String dni) {
        HorarioDAO horarioDAO = new HorarioDAOImp();
        horarioDAO.iniciarJornada(dni);
    }

    public void iniciarAlmuerzo(String dni) {
        HorarioDAO horarioDAO = new HorarioDAOImp();
        horarioDAO.iniciarAlmuerzo(dni);
    }

    public void finAlmuerzo(String dni) {
        HorarioDAO horarioDAO = new HorarioDAOImp();
        horarioDAO.finAlmuerzo(dni);
    }

    public void finJornada(String dni) {
        HorarioDAO horarioDAO = new HorarioDAOImp();
        horarioDAO.finJornada(dni);
    }
}

