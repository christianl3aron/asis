package net.bpogroup.horario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Christianl3aron on 13/03/2015.
 */
@Entity
@Table(name = "bh_condicion")
public class CondicionBean {

    @Id
    @Column(name = "idcondicion")
    int codigo;
    @Column(name = "condicion")
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

    @Override
    public String toString() {
        return "id=" + codigo + ", name=" + condicion;
    }
}
