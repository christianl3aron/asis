package net.bpogroup.horario.service.imp;

import net.bpogroup.horario.dao.UsuarioDAO;
import net.bpogroup.horario.dao.bean.UsuarioBean;
import net.bpogroup.horario.dao.imp.UsuarioDAOImp;
import net.bpogroup.horario.service.LoginService;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
public class LoginServiceImp implements LoginService {
    public LoginServiceImp() {
    }

    public UsuarioBean validarUsuario(String dni) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAOImp();
        return usuarioDAO.getUsuario(dni);
    }
}
