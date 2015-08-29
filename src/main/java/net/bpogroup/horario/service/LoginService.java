package net.bpogroup.horario.service;

import net.bpogroup.horario.model.UsuarioBean;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
public interface LoginService {
    UsuarioBean validarUsuario(String var1) throws Exception;
}
