package net.bpogroup.horario.dao.bean;

/**
 * Created by Christianl3aron on 13/03/2015.
 */
public class CondicionBean {

    int codigo;
    String condicion;

    public CondicionBean() {
    }

    public CondicionBean(int codigo, String condicion) {
        this.codigo = codigo;
        this.condicion = condicion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
}
