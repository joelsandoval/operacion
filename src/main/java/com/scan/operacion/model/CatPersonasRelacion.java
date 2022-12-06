/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scan.operacion.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "cat_personas_relacion")
@NamedQueries({
    @NamedQuery(name = "CatPersonasRelacion.findAll", query = "SELECT c FROM CatPersonasRelacion c")})
public class CatPersonasRelacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "moral")
    private Integer moral;
    @Column(name = "fisica")
    private Integer fisica;
    @Column(name = "hijo")
    private Integer hijo;

    public CatPersonasRelacion() {
    }

    public CatPersonasRelacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMoral() {
        return moral;
    }

    public void setMoral(Integer moral) {
        this.moral = moral;
    }

    public Integer getFisica() {
        return fisica;
    }

    public void setFisica(Integer fisica) {
        this.fisica = fisica;
    }

    public Integer getHijo() {
        return hijo;
    }

    public void setHijo(Integer hijo) {
        this.hijo = hijo;
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
        if (!(object instanceof CatPersonasRelacion)) {
            return false;
        }
        CatPersonasRelacion other = (CatPersonasRelacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scan.operacion.model.CatPersonasRelacion[ id=" + id + " ]";
    }
    
}
