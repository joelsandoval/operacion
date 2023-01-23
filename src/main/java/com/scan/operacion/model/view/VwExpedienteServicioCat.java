/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scan.operacion.model.view;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "vw_expediente_servicio_cat", schema = "operacion")
public class VwExpedienteServicioCat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "servicio")
    private Integer servicio;
    @OneToMany
    @JoinColumns({
        @JoinColumn(updatable = false, insertable = false, name = "tipo", referencedColumnName = "id"),
        @JoinColumn(updatable = false, insertable = false, name = "servicio", referencedColumnName = "servicio")
    })
    private List<VwExpedienteServicio> documentos;

    public VwExpedienteServicioCat() {
    }

    public VwExpedienteServicioCat(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getServicio() {
        return servicio;
    }

    public void setServicio(Integer servicio) {
        this.servicio = servicio;
    }

    public List<VwExpedienteServicio> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<VwExpedienteServicio> documentos) {
        this.documentos = documentos;
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
        if (!(object instanceof VwExpedienteServicioCat)) {
            return false;
        }
        VwExpedienteServicioCat other = (VwExpedienteServicioCat) object;
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
