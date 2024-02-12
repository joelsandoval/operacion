/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scan.operacion.model.view;

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
@Table(name = "vw_proyectos_servicios_actividades", schema = "operacion")
public class VwProyectosServiciosActividades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "servicio")
    private Integer servicio;
    @Column(name = "actividad_id")
    private Integer actividadId;
    @Column(name = "actividad")
    private String actividad;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "tipo_id")
    private Integer tipoId;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "estatus_id")
    private Integer estatusId;
    @Column(name = "estatus")
    private String estatus;
    @Column(name = "responsable_id")
    private Integer responsableId;
    @Column(name = "responsable")
    private String responsable;
    @Column(name = "clase")
    private String clase;
    @Column(name = "vencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vencimiento;
    @Column(name = "dias")
    private Integer dias;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "semaforo")
    private Integer semaforo;
    @Column(name = "terminado")
    private Boolean terminado;
    @Column(name = "observaciones")
    private String observaciones;

    public VwProyectosServiciosActividades() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServicio() {
        return servicio;
    }

    public void setServicio(Integer servicio) {
        this.servicio = servicio;
    }

    public Integer getActividadId() {
        return actividadId;
    }

    public void setActividadId(Integer actividadId) {
        this.actividadId = actividadId;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTipoId() {
        return tipoId;
    }

    public void setTipoId(Integer tipoId) {
        this.tipoId = tipoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(Integer estatusId) {
        this.estatusId = estatusId;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(Integer responsableId) {
        this.responsableId = responsableId;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(Integer semaforo) {
        this.semaforo = semaforo;
    }

    public String getSemaforoColor() {
        Integer smf = this.semaforo;
        Integer av = this.estado;
        String color = "badge-verde";
        switch (av) {
            case 0:
                color = "badge-rojo";
                break;
            case 1:
                color = "badge-azul";
                break;
            case 2:
                if (smf == 1) {
                    color = "badge-naranja";
                } else if (smf == 2) {
                    color = "badge-amarillo";
                } else {
                    color = "badge-verde";
                }
        }
        return color;
    }

    public String getSemaforoEtiqueta() {
        Integer sema = this.semaforo;
        Integer esta = this.estado;
        String etiqueta = "En tiempo";
        switch (esta) {
            case 0:
                etiqueta = "Vencido";
                break;
            case 1:
                etiqueta = "Vence hoy";
                break;
            case 2:
                if (sema == 1) {
                    etiqueta = "Vence pronto";
                } else if (sema == 2) {
                    etiqueta = "Por vencer";
                } else {
                    etiqueta = "En tiempo";
                }
        }
        return etiqueta;
    }

    public Boolean getTerminado() {
        return terminado;
    }

    public void setTerminado(Boolean terminado) {
        this.terminado = terminado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
