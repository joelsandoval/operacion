/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scan.operacion.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "proyectos_flujo", schema = "operacion")
public class ProyectosFlujo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nivel")
    private int nivel;
    @Basic(optional = false)
    @Column(name = "situacion")
    private int situacion;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "usuario_registra")
    private Integer usuarioRegistra;
    @Column(name = "usuario_recibe")
    private Integer usuarioRecibe;
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "referencia")
    private int referencia;

    public ProyectosFlujo() {
    }

    public ProyectosFlujo(Integer id) {
        this.id = id;
    }

    public ProyectosFlujo(Integer id, int nivel, int situacion, Date fecha, int referencia) {
        this.id = id;
        this.nivel = nivel;
        this.situacion = situacion;
        this.fecha = fecha;
        this.referencia = referencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getSituacion() {
        return situacion;
    }

    public void setSituacion(int situacion) {
        this.situacion = situacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getUsuarioRegistra() {
        return usuarioRegistra;
    }

    public void setUsuarioRegistra(Integer usuarioRegistra) {
        this.usuarioRegistra = usuarioRegistra;
    }

    public Integer getUsuarioRecibe() {
        return usuarioRecibe;
    }

    public void setUsuarioRecibe(Integer usuarioRecibe) {
        this.usuarioRecibe = usuarioRecibe;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyectosFlujo)) {
            return false;
        }
        ProyectosFlujo other = (ProyectosFlujo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scan.operacion.model.ProyectosFlujo[ id=" + id + " ]";
    }
    
}
