package net.bpogroup.horario.dao;

import net.bpogroup.horario.dao.bean.AreaBean;
import java.util.List;

/**
 * Created by Christianl3aron on 14/03/2015.
 */
public interface AreaDAO {
    List<AreaBean> getAreas() throws Exception;
}
