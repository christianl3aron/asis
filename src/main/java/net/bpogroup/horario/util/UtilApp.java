package net.bpogroup.horario.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    //Obtener la now() formateando y en cadena
    public static String getDate4Report() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date());
    }
}
