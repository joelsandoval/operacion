package com.scan.operacion.rest;

import com.scan.operacion.dao.CatPersonasMoralesRepository;
import com.scan.operacion.dao.CatServiciosRepository;
import com.scan.operacion.dao.ProyectosRepository;
import com.scan.operacion.dao.VwProyectosRepository;
import com.scan.operacion.model.CatPersonasMorales;
import com.scan.operacion.model.Proyectos;
import com.scan.operacion.model.generic.Par;
import com.scan.operacion.view.VwProyectos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author joel.sandoval
 */
@RestController
@RequestMapping(value = "catalogos")
@CrossOrigin
class CatalogosREST {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogosREST.class);

    @Autowired
    private CatServiciosRepository repoCatalogos;

    /**
     * Guarda la resolución de un trámite, Inserta un registro en la tabla
     * BITACORA_RESOLUCION
     *
     * @param resolucion se recibe un objeto BitacoraResolucionDTO
     * {@link BitacoraResolucionDTO} @RequestBody
     * @return
     */
//    @PostMapping(value = "resolucion")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public void createResolucion(@RequestBody BitacoraResolucionDTO resolucion) {
//        serviceT.guardaResolucion(bita);
//        LOGGER.info("Se creó la resolucion: " + bita.getBireBitacora());
//    }

    @GetMapping(value = "/sectores")
    public List<Par> dameSectores() {
        return repoCatalogos.dameSectores();
    }

//    @GetMapping(value = "/proyectos/activos")
//    public List<Proyectos> dameProyecto(@PathVariable("clave") String clave) {
//        return repoProyectos.dameProyectosActivos();
//    }
}
