package net.bpogroup.horario.service.imp;

import net.bpogroup.horario.dao.UsuarioDAO;
import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
@Service
public class LoginServiceImp implements LoginService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public UsuarioBean validarUsuario(String dni) throws Exception {
        return usuarioDAO.listOne(dni);
    }

}
