package com.scan.operacion.dao;

import com.scan.operacion.model.CatActividades;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface CatActividadesRepository extends PagingAndSortingRepository<CatActividades, Integer> {
   
      
}
