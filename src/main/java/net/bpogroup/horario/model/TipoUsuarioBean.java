package net.bpogroup.horario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Christianl3aron on 12/03/2015.
 */
@Entity
@Table(name = "bh_tipo_usuario")
public class TipoUsuarioBean {
    @Id
    @Column(name = "idtipo_usuario")
    private int codigo;
    @Column(name = "denominacion")
    private String denominacion;

    public TipoUsuarioBean() {
    }

    public TipoUsuarioBean(int codigo, String denominacion) {
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

    @Override
    public String toString() {
        return "id=" + codigo + ", name=" + denominacion;
    }
}
