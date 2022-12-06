package com.scan.operacion.dao;

import com.scan.operacion.view.VwProyectos;
import com.scan.operacion.view.VwProyectosServicios;
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
public interface VwProyectosServiciosRepository extends CrudRepository<VwProyectosServicios, Integer> {

    @Query(value = "select s.* from operacion.vw_proyectos_servicios s where s.proyecto = :proyecto", nativeQuery = true)
    public List<VwProyectosServicios> dameServicios(@Param("proyecto") Integer proyecto);
    
}
