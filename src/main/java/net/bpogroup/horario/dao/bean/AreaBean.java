package net.bpogroup.horario.dao.bean;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
public class AreaBean {
    private int codigo;
    private String denominacion;

    public AreaBean() {
    }

    public AreaBean(int codigo) {
        this.codigo = codigo;
    }

    public AreaBean(int codigo, String denominacion) {
        this.codigo = codigo;
        this.denominacion = denominacion;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getDenominacion() {
        return this.denominacion;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
}

