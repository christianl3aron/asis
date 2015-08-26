package net.bpogroup.horario.dao.bean;

/**
 * Created by Christianl3aron on 13/03/2015.
 */
public class CargoBean {

    int codigo;
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
}
