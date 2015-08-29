package net.bpogroup.horario.util;

import net.bpogroup.horario.model.AsistenciaBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Christianl3aron on 17/03/2015.
 */
public final class UtilApp {

    private UtilApp() {
    }

    public static Date getDate(String ti) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(ti);
    }

    //Agregar un dia para filtrar
    public static Date addADay(String tf) throws Exception {

        Calendar c = Calendar.getInstance();
        c.setTime(getDate(tf));
        c.add(5, 1);
        return c.getTime();
    }

    //Obtener now() formateando y en cadena
    public static String getDate4Report() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date());
    }

    public static List<AsistenciaBean> formatDateInAsistenciaBeanList(List<AsistenciaBean> list, int i, int l) {

        for (AsistenciaBean bean : list) {
            if (null != bean.getIniDia())
                bean.setIniDia(bean.getIniDia().substring(i, l));
            if (null != bean.getIniBreak())
                bean.setIniBreak(bean.getIniBreak().substring(i, l));
            if (null != bean.getFinBreak())
                bean.setFinBreak(bean.getFinBreak().substring(i, l));
            if (null != bean.getFinDia())
                bean.setFinDia(bean.getFinDia().substring(i, l));
        }
        return list;
    }
}
