package com.scan.operacion.dao;

import com.scan.operacion.model.ProyectosServiciosActividades;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface ProyectosServiciosActividadesRepository extends PagingAndSortingRepository<ProyectosServiciosActividades, Integer> {

    Optional<ProyectosServiciosActividades> findById(Integer id);
      
}
