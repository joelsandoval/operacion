/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scan.operacion.model.dto;

import com.scan.operacion.model.view.VwProyectosServicios;
import com.scan.operacion.model.view.VwProyectosServiciosActividades;

/**
 *
 * @author Joel
 */
public class ProyectosServiciosVencimiento {
    private VwProyectosServicios servicio;
    private VwProyectosServiciosActividades compromiso;

    public ProyectosServiciosVencimiento() {
    }

    public VwProyectosServicios getServicio() {
        return servicio;
    }

    public void setServicio(VwProyectosServicios servicio) {
        this.servicio = servicio;
    }

    public VwProyectosServiciosActividades getCompromiso() {
        return compromiso;
    }

    public void setCompromiso(VwProyectosServiciosActividades compromiso) {
        this.compromiso = compromiso;
    }
    
}