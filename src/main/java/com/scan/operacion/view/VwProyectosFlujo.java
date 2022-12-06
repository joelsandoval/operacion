/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scan.operacion.view;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "vw_proyectos_flujo", schema = "operacion")
public class VwProyectosFlujo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "nivel")
    private Integer nivel;
    @Column(name = "situacion_id")
    private Integer situacionId;
    @Column(name = "situacion")
    private String situacion;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "usuario_registra_id")
    private Integer usuarioRegistraId;
    @Column(name = "usuario_registra")
    private String usuarioRegistra;
    @Column(name = "usuario_recibe_id")
    private Integer usuarioRecibeId;
    @Column(name = "usuario_recibe")
    private String usuarioRecibe;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "referencia")
    private Integer referencia;
    @Column(name = "clase")
    private String clase;
    @Column(name = "icono")
    private String icono;

    public VwProyectosFlujo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getSituacionId() {
        return situacionId;
    }

    public void setSituacionId(Integer situacionId) {
        this.situacionId = situacionId;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getUsuarioRegistraId() {
        return usuarioRegistraId;
    }

    public void setUsuarioRegistraId(Integer usuarioRegistraId) {
        this.usuarioRegistraId = usuarioRegistraId;
    }

    public String getUsuarioRegistra() {
        return usuarioRegistra;
    }

    public void setUsuarioRegistra(String usuarioRegistra) {
        this.usuarioRegistra = usuarioRegistra;
    }

    public Integer getUsuarioRecibeId() {
        return usuarioRecibeId;
    }

    public void setUsuarioRecibeId(Integer usuarioRecibeId) {
        this.usuarioRecibeId = usuarioRecibeId;
    }

    public String getUsuarioRecibe() {
        return usuarioRecibe;
    }

    public void setUsuarioRecibe(String usuarioRecibe) {
        this.usuarioRecibe = usuarioRecibe;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getReferencia() {
        return referencia;
    }

    public void setReferencia(Integer referencia) {
        this.referencia = referencia;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
    
}
