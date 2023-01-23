package com.scan.operacion.rest;

import com.scan.operacion.dao.CatActividadesTipoRepository;
import com.scan.operacion.dao.CatServiciosCategoriaRepository;
import com.scan.operacion.dao.CatServiciosRepository;
import com.scan.operacion.dao.view.VwExpCatDocumentosServicioRepository;
import com.scan.operacion.model.CatActividadesTipo;
import com.scan.operacion.model.CatServicios;
import com.scan.operacion.model.CatServiciosCategoria;
import com.scan.operacion.model.generic.Par;
import com.scan.operacion.model.view.VwExpCatDocumentosServicio;
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
@RequestMapping(value = "catalogos")
@CrossOrigin
class CatalogosREST {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogosREST.class);

    @Autowired
    private CatServiciosRepository repoCatalogos;
    
    @Autowired
    private CatServiciosCategoriaRepository repoServiciosCatego;
    
    @Autowired
    private CatActividadesTipoRepository repoAcTipo;
    
    @Autowired
    private VwExpCatDocumentosServicioRepository repoDocsServ;

    /**
     * Guarda una categoría en el catálogo
     *
     * @param servicio se recibe un objeto CatServiciosCategoria
     * {@link BitacoraResolucionDTO} @RequestBody
     * @return
     */
    @PostMapping(value = "categoria")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CatServiciosCategoria createCategoria(@RequestBody CatServiciosCategoria catego) {
        CatServiciosCategoria result = new CatServiciosCategoria();
        try {
        result = repoServiciosCatego.save(catego);
        LOGGER.info("Se actualizó {}", catego.getCategoria());
        } catch (Exception e) {
            LOGGER.error("No se actualizó el servicio {} {}", catego.getCategoria(), e.getMessage());
        } 
        return result;
    }
    
    /**
     * Guarda un servicio en el catálogo
     *
     * @param servicio se recibe un objeto CatServicios
     * {@link BitacoraResolucionDTO} @RequestBody
     * @return
     */
    @PostMapping(value = "servicio")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CatServicios createServicio(@RequestBody CatServicios servicio) {
        CatServicios result = new CatServicios();
        try {
        result = repoCatalogos.save(servicio);
        LOGGER.info("Se actualizó {}", servicio.getServicio());
        } catch (Exception e) {
            LOGGER.error("No se actualizó el servicio {} {}", servicio.getServicioCorto(), e.getMessage());
        } 
        return result;
    }

    @GetMapping(value = "/sectores")
    public List<Par> dameSectores() {
        return repoCatalogos.dameSectores();
    }
    
    @GetMapping(value = "/categoria/delete/{catego}")
    public void delCategorias(@PathVariable("catego") Integer catego) {
        repoServiciosCatego.deleteById(catego);
    }
    
    @GetMapping(value = "/servicio/delete/{servicio}")
    public void delServicios(@PathVariable("servicio") Integer servicio) {
        repoCatalogos.deleteById(servicio);
    }
    
    @GetMapping(value = "/servicios/categorias")
    public List<CatServiciosCategoria> dameCategoriasServicios() {
        return repoServiciosCatego.dameCategorias();
    }

    @GetMapping(value = "/actividades/tipos")
    public Iterable<CatActividadesTipo> dameProyecto() {
        return repoAcTipo.findAll();
    }
    
    @GetMapping(value = "/servicio/documentos/{servicio}")
    public List<VwExpCatDocumentosServicio> dameDocumentosServicio(@PathVariable("servicio") Integer servicio) {
        return repoDocsServ.damePorServicio(servicio);
    }
}
