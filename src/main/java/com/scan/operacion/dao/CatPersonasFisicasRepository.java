package com.scan.operacion.dao;

import com.scan.operacion.model.CatPersonasFisicas;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface CatPersonasFisicasRepository extends PagingAndSortingRepository<CatPersonasFisicas, Integer> {

    @Query(value = "select pf.* from cat_personas_fisicas pf order by pf.nombre, pf.apellido1, pf.apellido2", nativeQuery = true)
    public List<CatPersonasFisicas> dameTodos();

    @Query(value = "select pf.* from cat_personas_fisicas pf \n"
            + "inner join  proyectos_equipo pe on pf.id = pe.persona\n"
            + "where pe.proyecto = :proyecto", nativeQuery = true)
    public List<CatPersonasFisicas> dameEquipoProyecto(@Param("proyecto") Integer proyecto);

}
