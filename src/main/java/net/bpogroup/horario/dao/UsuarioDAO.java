package net.bpogroup.horario.dao;

import net.bpogroup.horario.dao.bean.UsuarioBean;

import java.util.List;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
public interface UsuarioDAO {
    UsuarioBean getUsuario(String var1) throws Exception;
    List<UsuarioBean> getUsuarios() throws Exception;
}
