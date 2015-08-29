package net.bpogroup.horario.dao;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
public interface HorarioDAO {
    void iniciarJornada(String var1) throws Exception;

    void iniciarAlmuerzo(String var1) throws Exception;

    void finAlmuerzo(String var1) throws Exception;

    void finJornada(String var1) throws Exception;
}
