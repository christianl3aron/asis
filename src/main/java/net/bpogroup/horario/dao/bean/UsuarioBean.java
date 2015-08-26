package net.bpogroup.horario.dao.bean;

import java.util.Date;


/**
 * Created by Christianl3aron on 12/03/2015.
 */

public class UsuarioBean {
    private String dni;
    private String nombres;
    private String apellidos;
    private TipoUsuarioBean tipoUsuarioBean;
    private AreaBean areaBean;
    private EstadoBean estadoBean;
    private Date ult_inicio;
    private Date ult_fin;
    private CondicionBean condicionBean;
    private CargoBean cargoBean;


    public UsuarioBean() {
    }

    public UsuarioBean(String dni, String nombres, String apellidos) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public UsuarioBean(String dni, String nombres, String apellidos, AreaBean areaBean) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.areaBean = areaBean;
    }

    public UsuarioBean(String dni, String nombres, String apellidos, TipoUsuarioBean tipoUsuarioBean, EstadoBean estadoBean, Date ult_inicio, Date ult_fin) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoUsuarioBean = tipoUsuarioBean;
        this.estadoBean = estadoBean;
        this.ult_inicio = ult_inicio;
        this.ult_fin = ult_fin;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public AreaBean getAreaBean() {
        return this.areaBean;
    }

    public String getDni() {
        return this.dni;
    }

    public String getNombres() {
        return this.nombres;
    }

    public TipoUsuarioBean getTipoUsuarioBean() {
        return this.tipoUsuarioBean;
    }

    public EstadoBean getEstadoBean() {
        return this.estadoBean;
    }

    public Date getUlt_fin() {
        return this.ult_fin;
    }

    public Date getUlt_inicio() {
        return this.ult_inicio;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setAreaBean(AreaBean areaBean) {
        this.areaBean = areaBean;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setTipoUsuarioBean(TipoUsuarioBean tipoUsuarioBean) {
        this.tipoUsuarioBean = tipoUsuarioBean;
    }

    public void setEstadoBean(EstadoBean estadoBean) {
        this.estadoBean = estadoBean;
    }

    public void setUlt_fin(Date ult_fin) {
        this.ult_fin = ult_fin;
    }

    public void setUlt_inicio(Date ult_inicio) {
        this.ult_inicio = ult_inicio;
    }

    public CondicionBean getCondicionBean() {
        return condicionBean;
    }

    public void setCondicionBean(CondicionBean condicionBean) {
        this.condicionBean = condicionBean;
    }

    public CargoBean getCargoBean() {
        return cargoBean;
    }

    public void setCargoBean(CargoBean cargoBean) {
        this.cargoBean = cargoBean;
    }
}