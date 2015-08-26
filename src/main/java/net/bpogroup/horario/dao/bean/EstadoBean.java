package net.bpogroup.horario.dao.bean;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
public class EstadoBean {
    private int codigo;
    private String estado;

    public EstadoBean(int codigo, String estado) {
        this.codigo = codigo;
        this.estado = estado;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
