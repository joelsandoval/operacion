package com.scan.operacion.dao;

import com.scan.operacion.model.CatServiciosCategoria;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface CatServiciosCategoriaRepository extends PagingAndSortingRepository<CatServiciosCategoria, Integer> {

    Optional<CatServiciosCategoria> findById(Integer id);
      
}
