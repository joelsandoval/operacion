/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scan.operacion.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "cat_actividades_tipo", schema="operacion")
public class CatActividadesTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "actividad_tipo")
    private String actividadTipo;
    @OneToMany(mappedBy= "tipo")
    private List<CatActividades> actividades;

    public CatActividadesTipo() {
    }

    public CatActividadesTipo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActividadTipo() {
        return actividadTipo;
    }

    public void setActividadTipo(String actividadTipo) {
        this.actividadTipo = actividadTipo;
    }

    public List<CatActividades> getActividades() {
        return actividades;
    }

    public void setActividades(List<CatActividades> actividades) {
        this.actividades = actividades;
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
        if (!(object instanceof CatActividadesTipo)) {
            return false;
        }
        CatActividadesTipo other = (CatActividadesTipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scan.operacion.model.CatActividadesTipo[ id=" + id + " ]";
    }
    
}
