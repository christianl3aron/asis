package net.bpogroup.horario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Christianl3aron on 13/03/2015.
 */
@Entity
@Table(name = "bh_cargo")
public class CargoBean {

    @Id
    @Column(name = "idcargo")
    int codigo;
    @Column(name = "cargo")
    String cargo;

    public CargoBean() {
    }

    public CargoBean(int codigo, String cargo) {
        this.codigo = codigo;
        this.cargo = cargo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "id=" + codigo + ", name=" + cargo;
    }
}
