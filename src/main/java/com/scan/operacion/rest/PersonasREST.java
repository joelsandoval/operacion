package com.scan.operacion.rest;

import com.scan.operacion.dao.CatPersonasMoralesRepository;
import com.scan.operacion.dao.view.VwFisicasUsuariosRepository;
import com.scan.operacion.model.CatPersonasMorales;
import com.scan.operacion.model.view.VwFisicasUsuarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
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
@RequestMapping(value = "personas")
@CrossOrigin
class PersonasREST {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonasREST.class);

    @Autowired
    private CatPersonasMoralesRepository repoMorales;
    @Autowired
    private VwFisicasUsuariosRepository repoFisicasF;

    /**
     * Guarda la resolución de un trámite, Inserta un registro en la tabla
     * BITACORA_RESOLUCION
     *
     * @param resolucion se recibe un objeto BitacoraResolucionDTO
     * {@link BitacoraResolucionDTO} @RequestBody
     * @return
     */
    @PostMapping(value = "moral")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CatPersonasMorales createMoral(@RequestBody CatPersonasMorales moral) {
        CatPersonasMorales result = new CatPersonasMorales();
        try {
            result = repoMorales.save(moral);
            LOGGER.info("Se creó el cliente: {}", moral.getNombre_corto());
            
        } catch (Exception e) {
            LOGGER.error("No se actualizó el cliente {} {}", moral.getNombre_corto(), e.getMessage());
        }
        return result;
    }

    
    @GetMapping(value = "/morales/tipo/{tipo}")
    public List<CatPersonasMorales> dameProyecto(@PathVariable("tipo") Integer tipo) {
        return repoMorales.damePersonasPorTipo(tipo);
    }

    @GetMapping(value = "/fisicas/moral/{moral}")
    public List<VwFisicasUsuarios> dameFisicasPorMoral(@PathVariable("moral") Integer moral) {
        return repoFisicasF.dameUsuarios(moral);
    }
}
