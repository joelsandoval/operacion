package com.scan.operacion.dao;

import com.scan.operacion.model.security.SegUsuarios;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface SegUsuariosRepository extends CrudRepository<SegUsuarios, Integer> {

    Optional<SegUsuarios> findByUsuario(String usuario);

    @Query(value = "select su.* from seguridad.seg_usuarios su order by su.nombre, su.apellidos", nativeQuery = true)
    public List<SegUsuarios> dameTodos();

    @Query(value = "select distinct su.* from seguridad.seg_usuarios su \n"
            + "inner join  operacion.proyectos_equipo pe on su.id = pe.persona\n"
            + "where pe.proyecto = :proyecto order by su.nombre, su.apellidos", nativeQuery = true)
    public List<SegUsuarios> dameEquipoProyecto(@Param("proyecto") Integer proyecto);

}
