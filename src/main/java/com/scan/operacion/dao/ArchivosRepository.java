package com.scan.operacion.dao;

import com.scan.operacion.model.Archivos;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface ArchivosRepository extends PagingAndSortingRepository<Archivos, Integer> {

    Optional<Archivos> findById(Integer id);
      
}
