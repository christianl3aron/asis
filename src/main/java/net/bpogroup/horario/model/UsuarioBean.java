package net.bpogroup.horario.model;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * Created by Christianl3aron on 12/03/2015.
 */

@Entity
@Table(name = "bh_usuario")
public class UsuarioBean {

    @Id
    @Column(name = "dni")
    private String dni;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellidos")
    private String apellidos;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idtipo_usuario")
    private TipoUsuarioBean tipoUsuarioBean;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idarea")
    private AreaBean areaBean;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idestado")
    private EstadoBean estadoBean;
    @Column(name = "ult_inicio")
    private Timestamp ult_inicio;
    @Column(name = "ult_fin")
    private Timestamp ult_fin;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "condicion")
    private CondicionBean condicionBean;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcargo")
    private CargoBean cargoBean;
    @Column(name = "monitor")
    private String monitor;


    public UsuarioBean() {
    }

    public UsuarioBean(String dni) {
        this.dni = dni;
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

    public UsuarioBean(String dni, String nombres, String apellidos, TipoUsuarioBean tipoUsuarioBean, EstadoBean estadoBean, Timestamp ult_inicio, Timestamp ult_fin) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoUsuarioBean = tipoUsuarioBean;
        this.estadoBean = estadoBean;
        this.ult_inicio = ult_inicio;
        this.ult_fin = ult_fin;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public AreaBean getAreaBean() {
        return areaBean;
    }

    public void setAreaBean(AreaBean areaBean) {
        this.areaBean = areaBean;
    }

    public CargoBean getCargoBean() {
        return cargoBean;
    }

    public void setCargoBean(CargoBean cargoBean) {
        this.cargoBean = cargoBean;
    }

    public CondicionBean getCondicionBean() {
        return condicionBean;
    }

    public void setCondicionBean(CondicionBean condicionBean) {
        this.condicionBean = condicionBean;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public EstadoBean getEstadoBean() {
        return estadoBean;
    }

    public void setEstadoBean(EstadoBean estadoBean) {
        this.estadoBean = estadoBean;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public TipoUsuarioBean getTipoUsuarioBean() {
        return tipoUsuarioBean;
    }

    public void setTipoUsuarioBean(TipoUsuarioBean tipoUsuarioBean) {
        this.tipoUsuarioBean = tipoUsuarioBean;
    }

    public Timestamp getUlt_fin() {
        return ult_fin;
    }

    public void setUlt_fin(Timestamp ult_fin) {
        this.ult_fin = ult_fin;

    }

    public Timestamp getUlt_inicio() {
        return ult_inicio;
    }

    public void setUlt_inicio(Timestamp ult_inicio) {
        this.ult_inicio = ult_inicio;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    /*public Set<AsistenciaBean> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Set<AsistenciaBean> asistencias) {
        this.asistencias = asistencias;
    }*/
}