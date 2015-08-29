package net.bpogroup.horario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
@Entity
@Table(name = "bh_estado")
public class EstadoBean {

    @Id
    @Column(name = "idestado")
    private int codigo;
    @Column(name = "estado")
    private String estado;

    public EstadoBean() {
    }

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

    @Override
    public String toString() {
        return "id=" + codigo + ", name=" + estado;
    }
}
