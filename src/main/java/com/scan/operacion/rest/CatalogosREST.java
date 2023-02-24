package com.scan.operacion.rest;

import com.scan.operacion.dao.CatActividadesRepository;
import com.scan.operacion.dao.CatActividadesTipoRepository;
import com.scan.operacion.dao.CatServiciosCategoriaRepository;
import com.scan.operacion.dao.CatServiciosRepository;
import com.scan.operacion.dao.ExpCatDocumentosServiciosRepository;
import com.scan.operacion.dao.view.VwCatActividadesServiciosRepository;
import com.scan.operacion.dao.view.VwExpCatDocumentosServicioRepository;
import com.scan.operacion.model.CatActividades;
import com.scan.operacion.model.CatActividadesTipo;
import com.scan.operacion.model.CatServicios;
import com.scan.operacion.model.CatServiciosCategoria;
import com.scan.operacion.model.dto.Par;
import com.scan.operacion.model.view.VwCatActividadesServicios;
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
    private CatActividadesRepository repoActiv;

    @Autowired
    private CatActividadesTipoRepository repoAcTipo;

    @Autowired
    private VwExpCatDocumentosServicioRepository repoVwDocsServ;

    @Autowired
    private ExpCatDocumentosServiciosRepository repoDocsServ;

    @Autowired
    private VwCatActividadesServiciosRepository repoVwCatActServ;

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

    @GetMapping(value = "/servicio/documentos/delete/{doc}")
    public void delServicioDocs(@PathVariable("doc") Integer doc) {
        repoDocsServ.deleteById(doc);
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
        return repoVwDocsServ.damePorServicio(servicio);
    }

    /**
     * Guarda un servicio en el catálogo
     *
     * @param servicio se recibe un objeto CatServicios
     * {@link BitacoraResolucionDTO} @RequestBody
     * @return
     */
    @PostMapping(value = "actividad")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CatActividades createServicio(@RequestBody CatActividades actividad) {
        CatActividades result = new CatActividades();
        try {
            result = repoActiv.save(actividad);
            LOGGER.info("Se actualizó {}", actividad.getActividad());
        } catch (Exception e) {
            LOGGER.error("No se actualizó el servicio {} {}", actividad.getActividad(), e.getMessage());
        }
        return result;
    }

    @PostMapping(value = "actividad/tipo")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CatActividadesTipo createServicio(@RequestBody CatActividadesTipo actividad) {
        CatActividadesTipo result = new CatActividadesTipo();
        try {
            result = repoAcTipo.save(actividad);
            LOGGER.info("Se actualizó {}", actividad.getActividadTipo());
        } catch (Exception e) {
            LOGGER.error("No se actualizó el servicio {} {}", actividad.getActividadTipo(), e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "/actividad/delete/{act}")
    public void delActividad(@PathVariable("act") Integer act) {
        repoActiv.deleteById(act);
    }

    @GetMapping(value = "/actividad/tipo/delete/{act}")
    public void delActividadTipo(@PathVariable("act") Integer act) {
        repoAcTipo.deleteById(act);
    }

    @GetMapping(value = "/servicio/actividades/{servicio}")
    public List<VwCatActividadesServicios> dameActividadesServicio(@PathVariable("servicio") Integer servicio) {
        return repoVwCatActServ.findByServicio(servicio);
    }

}
