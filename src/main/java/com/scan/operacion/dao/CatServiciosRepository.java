package com.scan.operacion.dao;

import com.scan.operacion.model.CatServicios;
import com.scan.operacion.model.generic.Par;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface CatServiciosRepository extends PagingAndSortingRepository<CatServicios, Integer> {

    //Consultas Nativas
    @Query(value = "select c.id, c.sector as valor from operacion.cat_sectores c order by sector", nativeQuery = true)
    List<Par> dameSectores();
    
    
    
      
}
