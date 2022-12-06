package com.scan.operacion.dao;

import com.scan.operacion.model.CatPersonasFisicas;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface CatPersonasFisicasRepository extends PagingAndSortingRepository<CatPersonasFisicas, Integer> {

    Optional<CatPersonasFisicas> findById(Integer id);
      
}
