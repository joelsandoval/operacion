package com.scan.operacion.dao;

import com.scan.operacion.model.CatActividadesTipo;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface CatActividadesTipoRepository extends PagingAndSortingRepository<CatActividadesTipo, Integer> {

    Optional<CatActividadesTipo> findById(Integer id);
   
      
}
