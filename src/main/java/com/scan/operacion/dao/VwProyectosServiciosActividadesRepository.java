package com.scan.operacion.dao;

import com.scan.operacion.view.VwProyectosServiciosActividades;
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
public interface VwProyectosServiciosActividadesRepository extends PagingAndSortingRepository<VwProyectosServiciosActividades, Integer> {

    Optional<VwProyectosServiciosActividades> findById(Integer id);
    
    @Query(value = "select a.* from operacion.vw_proyectos_servicios_actividades a where a.servicio = :servicio order by a.fecha desc", nativeQuery = true)
    public List<VwProyectosServiciosActividades> dameActividades(@Param("servicio") Integer servicio);
      
}
