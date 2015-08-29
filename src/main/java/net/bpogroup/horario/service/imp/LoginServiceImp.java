package net.bpogroup.horario.service.imp;

import net.bpogroup.horario.dao.UsuarioDAO;
import net.bpogroup.horario.model.UsuarioBean;
import net.bpogroup.horario.dao.impl.UsuarioDAOImp;
import net.bpogroup.horario.service.LoginService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
public class LoginServiceImp implements LoginService {

    public UsuarioBean validarUsuario(String dni) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        UsuarioDAO usuarioDAO = context.getBean(UsuarioDAO.class);
        return usuarioDAO.listOne(dni);
    }
}
