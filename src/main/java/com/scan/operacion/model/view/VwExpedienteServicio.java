/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scan.operacion.model.view;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "vw_expediente_servicio", schema = "operacion")
public class VwExpedienteServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id")
    @Id
    private Integer id;
    @Column(name = "id_documento")
    private Integer idDocumento;
    @Column(name = "documento")
    private String documento;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cumple")
    private Boolean cumple;
    @Column(name = "comentarios")
    private String comentarios;
    @Column(name = "servicio")
    private Integer servicio;
    @Column(name = "tipo")
    private Integer tipo;
    @Column(name = "orden")
    private Integer orden;
    @Column(name = "persona")
    private Integer persona;
    @Column(name = "presenta")
    private Boolean presenta;
    @Column(name = "archivos")
    private Integer archivos;

    public VwExpedienteServicio() {
        //hacer algo.
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getCumple() {
        return cumple;
    }

    public void setCumple(Boolean cumple) {
        this.cumple = cumple;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getServicio() {
        return servicio;
    }

    public void setServicio(Integer servicio) {
        this.servicio = servicio;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getPersona() {
        return persona;
    }

    public void setPersona(Integer persona) {
        this.persona = persona;
    }

    public Boolean getPresenta() {
        return presenta;
    }

    public void setPresenta(Boolean presenta) {
        this.presenta = presenta;
    }

    public Integer getArchivos() {
        return archivos;
    }

    public void setArchivos(Integer archivos) {
        this.archivos = archivos;
    }

    
    
}
