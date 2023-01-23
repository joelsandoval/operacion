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
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "exp_cat_documentos_catego", schema = "operacion")
public class ExpCatDocumentosCatego implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "proceso")
    private Integer proceso;
    @OneToMany(mappedBy= "tipo")
    @OrderBy("documento")
    private List<ExpCatDocumentos> documentos;

    public ExpCatDocumentosCatego() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProceso() {
        return proceso;
    }

    public void setProceso(Integer proceso) {
        this.proceso = proceso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<ExpCatDocumentos> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<ExpCatDocumentos> documentos) {
        this.documentos = documentos;
    }

    


}
