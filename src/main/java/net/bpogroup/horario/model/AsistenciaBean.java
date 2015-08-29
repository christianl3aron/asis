package net.bpogroup.horario.model;

import javax.persistence.*;

/**
 * Created by Christianl3aron on 13/03/2015.
 */
@Entity
@Table(name = "bh_horario")
public class AsistenciaBean {

    @ManyToOne
    @JoinColumn(name = "dni",referencedColumnName = "dni")
    private UsuarioBean usuarioBean;
    @Id
    @Column(name = "dia")
    private String dia;
    @Column(name = "ini_jornada")
    private String iniDia;
    @Column(name = "ini_break")
    private String iniBreak;
    @Column(name = "fin_break")
    private String finBreak;
    @Column(name = "fin_jornada")
    private String finDia;

    public AsistenciaBean() {
    }

    public AsistenciaBean(UsuarioBean usuarioBean, String dia, String iniDia) {
        this.usuarioBean = usuarioBean;
        this.dia = dia;
        this.iniDia = iniDia;
    }

    public AsistenciaBean(UsuarioBean usuarioBean, String dia, String iniDia, String iniBreak, String finBreak, String finDia) {
        this.usuarioBean = usuarioBean;
        this.dia = dia;
        this.iniDia = iniDia;
        this.iniBreak = iniBreak;
        this.finBreak = finBreak;
        this.finDia = finDia;
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getIniDia() {
        return iniDia;
    }

    public void setIniDia(String iniDia) {
        this.iniDia = iniDia;
    }

    public String getIniBreak() {
        return iniBreak;
    }

    public void setIniBreak(String iniBreak) {
        this.iniBreak = iniBreak;
    }

    public String getFinBreak() {
        return finBreak;
    }

    public void setFinBreak(String finBreak) {
        this.finBreak = finBreak;
    }

    public String getFinDia() {
        return finDia;
    }

    public void setFinDia(String finDia) {
        this.finDia = finDia;
    }
}
