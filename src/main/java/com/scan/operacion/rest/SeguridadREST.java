package com.scan.operacion.rest;

import com.scan.operacion.dao.SegUsuariosRepository;
import com.scan.operacion.model.security.SegUsuarios;
import com.scan.operacion.services.KeycloakService;
import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    
    @Autowired
    private SegUsuariosRepository repoU;

    /**
     * Guarda el usuario en la base de datos de KeyCloack
     * UserRepresentstion
     *
     * @param user se recibe un objeto UserRepresentation del front
     * @return el objeto UserRepresentation actualizado
     */
    @PostMapping("/create-user")
    public UserRepresentation create(@RequestBody UserRepresentation user) {
        
        return kcService.createUser(user);
    }

    @GetMapping(value = "/roles")
    public List<RoleRepresentation> dameRoles() {
        Comparator<RoleRepresentation> byName = Comparator.comparing(RoleRepresentation::getName);
        List<RoleRepresentation> roles = kcService.getRolesResource().list();
        Collections.sort(roles, byName);
        return roles;
    }

    @GetMapping(value = "/users")
    public List<UserRepresentation> dameUsuarios() {
        return kcService.getUsersResource().list();
    }

    @GetMapping(value = "/user/{id}")
    public UserRepresentation dameUsuario(@PathVariable("id") String id) {
        return kcService.getUser(id);
    }

    @GetMapping(value = "/delete-user/{id}")
    public void delUsuario(@PathVariable("id") String id) {
        kcService.deleteUser(id);
    }

    @GetMapping(value = "/user-roles/{id}")
    public List<RoleRepresentation> dameUserRealmRoles(@PathVariable("id") String id) {
        return kcService.getUsersRealmRoles(id).listAll();
    }
    
     /**
     * Edita un usuario en la base de datos de KeyCloack
     * UserRepresentstion
     *
     * @param user se recibe un objeto UserRepresentation del front
     * @return el objeto UserRepresentation actualizado
     */
    @PostMapping("/edit-user")
    public UserRepresentation editUser(@RequestBody UserRepresentation user) {
        return kcService.editUser(user);
    }
    
    @PostMapping("/edit-user-creds")
    public UserRepresentation editUserCreds(@RequestBody UserRepresentation user) {
        return kcService.editUser(user);
    }
    
    
    @PostMapping("/usuario")
    public SegUsuarios create(@RequestBody SegUsuarios user) {
        
        return repoU.save(user);
    }
    
    @GetMapping(value = "/usuario/por-usuario/{usuario}")
    public SegUsuarios dameUsuarioPorUsuario(@PathVariable("usuario") String usuario) {
        return repoU.findByUsuario(usuario).get();
    }
}
