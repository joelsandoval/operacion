package com.scan.operacion.rest;

import com.scan.operacion.dao.CatPersonasFisicasRepository;
import com.scan.operacion.dao.ProyectosEquipoRepository;
import com.scan.operacion.dao.ProyectosFlujoRepository;
import com.scan.operacion.dao.ProyectosRepository;
import com.scan.operacion.dao.ProyectosServiciosActividadesRepository;
import com.scan.operacion.dao.ProyectosServiciosRepository;
import com.scan.operacion.dao.view.VwProyectosFlujoRepository;
import com.scan.operacion.dao.view.VwProyectosRepository;
import com.scan.operacion.dao.view.VwProyectosServiciosActividadesRepository;
import com.scan.operacion.dao.view.VwProyectosServiciosRepository;
import com.scan.operacion.model.CatPersonasFisicas;
import com.scan.operacion.model.Proyectos;
import com.scan.operacion.model.ProyectosEquipo;
import com.scan.operacion.model.ProyectosFlujo;
import com.scan.operacion.model.ProyectosServicios;
import com.scan.operacion.model.ProyectosServiciosActividades;
import com.scan.operacion.model.dto.ProyectosServiciosVencimiento;
import com.scan.operacion.model.view.VwProyectos;
import com.scan.operacion.model.view.VwProyectosFlujo;
import com.scan.operacion.model.view.VwProyectosServicios;
import com.scan.operacion.model.view.VwProyectosServiciosActividades;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author joel.sandoval
 */
@RestController
@RequestMapping(value = "proyectos")
@CrossOrigin
class ProyectosREST {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProyectosREST.class);

    @Autowired
    private ProyectosRepository repoProyectos;

    @Autowired
    private VwProyectosRepository repoVwProyectos;

    @Autowired
    private ProyectosServiciosRepository repoServicios;

    @Autowired
    private VwProyectosServiciosRepository repoVwServicios;

    @Autowired
    private ProyectosServiciosActividadesRepository repoActividades;

    @Autowired
    private VwProyectosServiciosActividadesRepository repoVwActividades;

    @Autowired
    private ProyectosFlujoRepository repoFlujo;

    @Autowired
    private VwProyectosFlujoRepository repoVwFlujo;

    @Autowired
    private ProyectosEquipoRepository repoEquipos;
    
    @Autowired
    private CatPersonasFisicasRepository repoPersonas;
    
    /**
     * Guarda la resolución de un trámite, Inserta un registro en la tabla
     * BITACORA_RESOLUCION
     *
     * @param resolucion se recibe un objeto BitacoraResolucionDTO
     * {@link BitacoraResolucionDTO} @RequestBody
     * @return
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Optional<VwProyectos> save(@RequestBody Proyectos proyecto) {
        Proyectos nuevo = repoProyectos.save(proyecto);
        LOGGER.info("Se creó el proyecto: {} {}", nuevo.getProyecto(), nuevo.getId());
        return repoVwProyectos.findById(nuevo.getId());
    }

    /**
     * Guarda la resolución de un trámite, Inserta un registro en la tabla
     * BITACORA_RESOLUCION
     *
     * @param resolucion se recibe un objeto BitacoraResolucionDTO
     * {@link BitacoraResolucionDTO} @RequestBody
     * @return
     */
    @GetMapping(value = "/activos")
    //@RolesAllowed("backend-user")
    public List<VwProyectos> dameProyectos() {
        LOGGER.info("dameProyectos Activos");
        return repoVwProyectos.dameProyectosActivos();
    }

    @GetMapping(value = "/{proyecto}")
    public VwProyectos dameProyecto(@PathVariable("proyecto") Integer proyecto) {
        LOGGER.info("entro a buscar el proyecto {}", proyecto);
        return repoVwProyectos.damePorId(proyecto).get();
    }

    @PostMapping(value = "/servicio")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public VwProyectosServicios saveServicio(@RequestBody ProyectosServicios servicio) {
        ProyectosServicios nuevo = repoServicios.save(servicio);
        LOGGER.info("Se creó el servicio: {}", nuevo.getId());
        return repoVwServicios.damePorId(nuevo.getId()).get();
    }

    @GetMapping(value = "/servicios/{proyecto}")
    public List<VwProyectosServicios> dameProyectoServicios(@PathVariable("proyecto") Integer proyecto) {
        return repoVwServicios.dameServicios(proyecto);
    }

    @GetMapping(value = "/servicios/vencimiento/{proyecto}")
    public List<ProyectosServiciosVencimiento> dameProyectoServiciosVencimiento(@PathVariable("proyecto") Integer proyecto) {

        List<ProyectosServiciosVencimiento> resultado = new ArrayList<>();
        List<VwProyectosServicios> servicios = repoVwServicios.dameServicios(proyecto);
        for (VwProyectosServicios servicio: servicios) {
                    Integer serv = servicio.getId();
                    Optional<VwProyectosServiciosActividades> compromiso = repoVwActividades.dameProximoVencimiento(serv);
                    ProyectosServiciosVencimiento servi = new ProyectosServiciosVencimiento();
                    servi.setServicio(servicio);    
                    if(compromiso.isPresent()) {
                        servi.setCompromiso(compromiso.get());
                    }
                    resultado.add(servi);
                }
        return resultado;
    }

    @GetMapping(value = "/servicio/{servicio}")
    public Optional<VwProyectosServicios> dameProyectoServicio(@PathVariable("servicio") Integer servicio) {
        return repoVwServicios.findById(servicio);
    }

    @PostMapping(value = "/actividades")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public VwProyectosServiciosActividades saveActividad(@RequestBody ProyectosServiciosActividades actividad) {
        ProyectosServiciosActividades nuevo = repoActividades.save(actividad);
        LOGGER.info("Se creó el proyecto: {}", nuevo.getActividad());
        return repoVwActividades.findById(nuevo.getId()).get();
    }

    @GetMapping(value = "/actividades/{servicio}")
    public List<VwProyectosServiciosActividades> dameProyectoServicioActividades(@PathVariable("servicio") Integer servicio) {
        return repoVwActividades.dameActividades(servicio);
    }

    @GetMapping(value = "/actividad/{actividad}")
    public Optional<VwProyectosServiciosActividades> dameProyectoServicioActividad(@PathVariable("actividad") Integer actividad) {
        return repoVwActividades.findById(actividad);
    }

    @GetMapping(value = "/vencimiento/{servicio}")
    public ProyectosServiciosVencimiento dameProximoVencimiento(@PathVariable("servicio") Integer servicio) {
        ProyectosServiciosVencimiento resultado = new ProyectosServiciosVencimiento();
        resultado.setServicio(repoVwServicios.damePorId(servicio).get());
        resultado.setCompromiso(repoVwActividades.dameProximoVencimiento(servicio).get());
        LOGGER.info("regresa el vencimiento {}", servicio);
        return resultado;
    }

    @GetMapping(value = "/actividad/borra/{actividad}")
    public void delProyectoServicioActividad(@PathVariable("actividad") Integer actividad) {
        repoActividades.deleteById(actividad);
    }

    @PostMapping(value = "/flujo")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProyectosFlujo saveFlujo(@RequestBody ProyectosFlujo flujo) {
        return repoFlujo.save(flujo);
    }

    @GetMapping(value = "/flujo/{proyecto},{nivel}")
    public List<VwProyectosFlujo> dameProyectoFlujo(@PathVariable("proyecto") Integer proyecto, @PathVariable("nivel") Integer nivel) {
        return repoVwFlujo.dameProyectoFlujo(proyecto, nivel);
    }
    
    @PostMapping(value = "/equipo")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CatPersonasFisicas save(@RequestBody ProyectosEquipo equipo) {
        ProyectosEquipo nuevo = repoEquipos.save(equipo);
        LOGGER.info("Se creó el equipo: {} {}", nuevo.getProyecto(), nuevo.getPersona());
        return repoPersonas.findById(nuevo.getPersona()).get();
    }

    
    @GetMapping(value = "/equipo/{proyecto}")
    public List<CatPersonasFisicas> dameProyectoEquipo(@PathVariable("proyecto") Integer proyecto) {
        return repoPersonas.dameEquipoProyecto(proyecto);
    }
    
    
    
}
