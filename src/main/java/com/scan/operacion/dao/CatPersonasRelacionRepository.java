package com.scan.operacion.dao;

import com.scan.operacion.model.CatPersonasRelacion;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface CatPersonasRelacionRepository extends PagingAndSortingRepository<CatPersonasRelacion, Integer> {

    Optional<CatPersonasRelacion> findById(Integer id);
      
}
