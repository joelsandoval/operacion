package com.scan.operacion.rest;

import com.scan.operacion.model.Proyectos;
import com.scan.operacion.model.dto.ResponseMessage;
import com.scan.operacion.model.security.User;
import com.scan.operacion.model.view.VwProyectos;
import com.scan.operacion.services.KeycloakService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     * Guarda la resolución de un trámite, Inserta un registro en la tabla
     * BITACORA_RESOLUCION
     *
     * @param resolucion se recibe un objeto BitacoraResolucionDTO
     * {@link BitacoraResolucionDTO} @RequestBody
     * @return
     */
    @PostMapping("/create-user")
    public ResponseEntity<ResponseMessage> create(@RequestBody User user) {
        Object[] obj = kcService.createUser(user);
        int status = (int) obj[0];
        ResponseMessage message = (ResponseMessage) obj[1];
        return ResponseEntity.status(status).body(message);
    }

    @GetMapping(value = "/roles")
    public List<RoleRepresentation> dameRoles() {
        return kcService.getRolesResource().list();
    }

    @GetMapping(value = "/users")
    public List<UserRepresentation> dameUsuarios() {
        return kcService.getUsersResource().list();
    }

    @GetMapping(value = "/user/{id}")
    public UserRepresentation dameUsuario(@PathVariable("id") String id) {
        return kcService.editUser(id);
    }

    @GetMapping(value = "/delete-user/{id}")
    public void delUsuario(@PathVariable("id") String id) {
        kcService.deleteUser(id);
    }
}
