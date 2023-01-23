package com.scan.operacion.dao;

import com.scan.operacion.model.ExpServicioArchivos;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
//@Service
@Repository
public interface ExpServicioArchivosRepository extends PagingAndSortingRepository<ExpServicioArchivos, Integer> {

}
