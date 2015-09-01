package net.bpogroup.horario.dao;

import net.bpogroup.horario.model.UsuarioBean;

import java.util.List;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
public interface UsuarioDAO {

    UsuarioBean listOne(String dni) throws Exception;

    List<UsuarioBean> listAll() throws Exception;

}
