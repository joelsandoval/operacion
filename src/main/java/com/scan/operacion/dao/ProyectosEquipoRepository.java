package com.scan.operacion.dao;

import com.scan.operacion.model.Archivos;
import com.scan.operacion.model.ProyectosEquipo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface ProyectosEquipoRepository extends CrudRepository<ProyectosEquipo, Integer> {

    @Query(value = "delete from operacion.proyectos_equipo  \n"
            + "where proyecto = :proyecto and persona = :usuario", nativeQuery = true)
    public void borraEquipo(@Param("proyecto") Integer proyecto, @Param("usuario") Integer usuario);
    
}
