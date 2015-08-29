package net.bpogroup.horario.dao;

import net.bpogroup.horario.model.UsuarioBean;

import java.util.List;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
public interface UsuarioDAO {
    UsuarioBean getUsuario(String dni) throws Exception;

    List<UsuarioBean> getUsuarios() throws Exception;

    void save(UsuarioBean usuarioBean) throws Exception;

    UsuarioBean listOne(String dni) throws Exception;

    List<UsuarioBean> listAll() throws Exception;

}
