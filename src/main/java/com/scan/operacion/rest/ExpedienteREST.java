package com.scan.operacion.rest;

import com.scan.operacion.dao.ExpCatDocumentosCategoRepository;
import com.scan.operacion.dao.ExpCatDocumentosRepository;
import com.scan.operacion.dao.ExpCatDocumentosServiciosRepository;
import com.scan.operacion.dao.ExpServicioArchivosRepository;
import com.scan.operacion.dao.ExpServicioRepository;
import com.scan.operacion.dao.view.VwExpedienteServicioCatRepository;
import com.scan.operacion.dao.view.VwExpedienteServicioRepository;
import com.scan.operacion.model.ExpCatDocumentos;
import com.scan.operacion.model.ExpCatDocumentosCatego;
import com.scan.operacion.model.ExpCatDocumentosServicios;
import com.scan.operacion.model.ExpServicio;
import com.scan.operacion.model.ExpServicioArchivos;
import com.scan.operacion.model.view.VwExpedienteServicio;
import com.scan.operacion.model.view.VwExpedienteServicioCat;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author joel.sandoval
 */
@RestController
@RequestMapping(value = "expediente")
class ExpedienteREST {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpedienteREST.class);

    @Autowired
    private VwExpedienteServicioRepository repository;

    @Autowired
    private VwExpedienteServicioCatRepository repoCat;

    @Autowired
    private ExpServicioRepository repositoryE;

    @Autowired
    private ExpServicioArchivosRepository repositoryEA;

    @Autowired
    private ExpCatDocumentosRepository repoCatDocs;

    @Autowired
    private ExpCatDocumentosServiciosRepository repoCatDocsServ;

    @Autowired
    private ExpCatDocumentosCategoRepository repoCategoDocs;

    @GetMapping(value = "tramite/{tramite},{tipo}")
    @ResponseBody
    public List<VwExpedienteServicio> damePorTramiteTipo(@PathVariable("tramite") Integer tramite, @PathVariable("tipo") Integer tipo) {
        return repository.damePorTramiteTipo(tramite, tipo);
    }

    @PostMapping(value = "/guarda")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public VwExpedienteServicio saveExpediente(@RequestBody ExpServicio resource) {
        VwExpedienteServicio result = new VwExpedienteServicio();
        try {
            ExpServicio nuevo = repositoryE.save(resource);
            LOGGER.info("Se guardó en el expediente");
            result = repository.findById(nuevo.getId()).get();
            LOGGER.info("Se recupera el nuevo expediente");
        } catch (Exception e) {
            LOGGER.error("Hubo un error en el guardado");
        }
        return result;
    }

    @GetMapping(value = "catalogo/tipo/{tipo},{persona},{servicio}")
    @ResponseBody
    public List<ExpCatDocumentos> dameCatalogoDocs(@PathVariable("tipo") Integer tipo, @PathVariable("persona") Integer persona, @PathVariable("servicio") Integer servicio) {
        return repoCatDocs.damePorTipoPersonaNoServicio(tipo, persona, servicio);
    }

    @PostMapping(value = "/catalogo/guarda")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ExpCatDocumentos saveExpediente(@RequestBody ExpCatDocumentos resource) {
        return repoCatDocs.save(resource);
    }

    @PostMapping(value = "/archivos/guarda")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ExpServicioArchivos saveExpedienteA(@RequestBody ExpServicioArchivos resource) {
        return repositoryEA.save(resource);
    }

    @GetMapping(value = "/servicio/{servicio}")
    @ResponseBody
    public List<VwExpedienteServicio> damePorServicio(@PathVariable("servicio") Integer servicio) {
        return repository.damePorServicio(servicio);
    }

    @GetMapping(value = "/crea/{servicio},{estudio},{usuario}")
    @ResponseBody
    public List<VwExpedienteServicioCat> ingresados(@PathVariable("servicio") Integer servicio, @PathVariable("estudio") Integer estudio,
            @PathVariable("usuario") Integer usuario) {

        LOGGER.info("Se intenta generar el expediente para el trámite: {}, {}, {}", servicio, estudio, usuario);
        List<VwExpedienteServicioCat> result = new ArrayList<>();
        String msg = "";
        try {
            repositoryE.creaServicioExpediente(servicio, estudio, usuario);
            msg = "se generó el expediente: " + servicio;
            LOGGER.info(msg);
            try {
                result = repoCat.findByServicioOrderById(servicio);
            } catch (Exception e) {
                msg = "No se recuperó el expediente: " + e.getMessage();
                LOGGER.error(msg);
            }
        } catch (Exception e) {
            msg = "No se generó el expediente: " + servicio;
            LOGGER.error(msg);
        }
        return result;
    }

    @GetMapping(value = "/cat/servicio/{servicio}")
    @ResponseBody
    public List<VwExpedienteServicioCat> dameCatPorServicio(@PathVariable("servicio") Integer servicio) {
        return repoCat.findByServicioOrderById(servicio);
    }

    @GetMapping(value = "/cat/categorias")
    @ResponseBody
    public List<ExpCatDocumentosCatego> dameCatPorServicio() {
        return repoCategoDocs.dameTodasCategorias();
    }

    @PostMapping(value = "/documentos/servicios/guarda")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ExpCatDocumentosServicios saveExpDocumntosServicios(@RequestBody ExpCatDocumentosServicios resource) {
        return repoCatDocsServ.save(resource);
    }
}
