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
import com.scan.operacion.services.KeycloakService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.RolesRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
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
@RequestMapping(value = "seguridad")
@CrossOrigin
class SeguridadREST {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeguridadREST.class);

    @Autowired
    private KeycloakService kcService;

    
 
    @GetMapping(value = "/roles")
    public List<RoleRepresentation> dameRoles() {
        return kcService.getRolesResource().list();
    }

    @GetMapping(value = "/users")
    public List<UserRepresentation> dameUsuarios() {
        return kcService.getUsersResource().list();
    }
   

}
