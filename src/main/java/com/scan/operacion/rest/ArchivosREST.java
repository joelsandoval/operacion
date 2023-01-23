package com.scan.operacion.rest;


import com.scan.operacion.commons.MyResourceNotFoundException;
import com.scan.operacion.commons.RestPreconditions;
import com.scan.operacion.dao.ArchivosRepository;
import com.scan.operacion.model.Archivos;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author joel.sandoval
 */
@RestController
@RequestMapping(value = "archivos")
class ArchivosREST {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ArchivosREST.class);
    
    @Autowired
    private ArchivosRepository repository;
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Archivos create(@RequestBody Archivos resource) {
        
        Archivos resulta = repository.save(resource);

        return resulta;
    }

    
    @GetMapping(value = "/expediente/{expediente}")
    @ResponseBody
    public List<Archivos> porExpediente(@PathVariable("expediente") Integer expediente) throws MyResourceNotFoundException {
        return RestPreconditions.checkFound(repository.dameArchivosExpediente(expediente));
    }

    @GetMapping(value = "/expediente/servicio/{servicio},{tipo}")
    @ResponseBody
    public List<Archivos> porExpedienteTT(@PathVariable("tramite") Integer tramite, @PathVariable("tipo") Integer tipo) throws MyResourceNotFoundException {
        return RestPreconditions.checkFound(repository.dameArchivosExpedienteTT(tramite,tipo));
    }
    
    
}
