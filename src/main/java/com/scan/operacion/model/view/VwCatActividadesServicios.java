/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scan.operacion.model.view;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "vw_cat_actividades_servicios", schema = "operacion")
public class VwCatActividadesServicios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    private Integer id;
    @Column(name = "servicio")
    private Integer servicio;
    @Column(name = "actual_id")
    private Integer actualId;
    @Column(name = "actual")
    private String actual;
    @Column(name = "futura_id")
    private Integer futuraId;
    @Column(name = "futura")
    private String futura;
    @Column(name = "dias")
    private Integer dias;

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

    public Integer getActualId() {
        return actualId;
    }

    public void setActualId(Integer actualId) {
        this.actualId = actualId;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public Integer getFuturaId() {
        return futuraId;
    }

    public void setFuturaId(Integer futuraId) {
        this.futuraId = futuraId;
    }

    public String getFutura() {
        return futura;
    }

    public void setFutura(String futura) {
        this.futura = futura;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }


}
