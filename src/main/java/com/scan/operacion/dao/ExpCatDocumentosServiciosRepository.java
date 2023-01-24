package com.scan.operacion.dao;

import com.scan.operacion.model.ExpCatDocumentosServicios;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
//@Service
@Repository
public interface ExpCatDocumentosServiciosRepository extends PagingAndSortingRepository<ExpCatDocumentosServicios, Integer> {

    

}
