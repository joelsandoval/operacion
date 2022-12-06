package com.scan.operacion.dao;

import com.scan.operacion.model.ProyectosServiciosActividades;
import com.scan.operacion.view.VwProyectosServiciosActividades;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface VwProyectosServiciosActividadesRepository extends PagingAndSortingRepository<VwProyectosServiciosActividades, Integer> {

    Optional<VwProyectosServiciosActividades> findById(Integer id);
      
}
