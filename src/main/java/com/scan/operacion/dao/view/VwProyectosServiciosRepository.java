package com.scan.operacion.dao.view;

import com.scan.operacion.model.view.VwProyectos;
import com.scan.operacion.model.view.VwProyectosServicios;
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
public interface VwProyectosServiciosRepository extends CrudRepository<VwProyectosServicios, Integer> {

    @Query(value = "select s.* from operacion.vw_proyectos_servicios s where s.id = :id", nativeQuery = true)
    public Optional<VwProyectosServicios> damePorId(@Param("id") Integer id);
    
    @Query(value = "select s.* from operacion.vw_proyectos_servicios s where s.proyecto = :proyecto", nativeQuery = true)
    public List<VwProyectosServicios> dameServicios(@Param("proyecto") Integer proyecto);
    
}
