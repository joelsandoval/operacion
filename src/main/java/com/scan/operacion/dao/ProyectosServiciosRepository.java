package com.scan.operacion.dao;

import com.scan.operacion.model.ProyectosServicios;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface ProyectosServiciosRepository extends PagingAndSortingRepository<ProyectosServicios, Integer> {

    Optional<ProyectosServicios> findById(Integer id);
      
}
