/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scan.operacion.view;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "vw_proyectos", schema = "operacion")
public class VwProyectos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id")
    @Id
    private Integer id;
    @Column(name = "proyecto")
    private String proyecto;
    @Column(name = "proyecto_corto")
    private String proyectoCorto;
    @Column(name = "cliente_id")
    private Integer clienteId;
    @Column(name = "registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registro;
    @Column(name = "estatus_id")
    private Integer estatusId;
    @Column(name = "cliente")
    private String cliente;
    @Column(name = "sector")
    private String sector;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "ubicacion")
    private String ubicacion;
    @Column(name = "categoria")
    private Integer categoria;
    

    public VwProyectos() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getProyectoCorto() {
        return proyectoCorto;
    }

    public void setProyectoCorto(String proyectoCorto) {
        this.proyectoCorto = proyectoCorto;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Date getRegistro() {
        return registro;
    }

    public void setRegistro(Date registro) {
        this.registro = registro;
    }

    public Integer getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(Integer estatusId) {
        this.estatusId = estatusId;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }
    
}
