package com.scan.operacion.dao;

import com.scan.operacion.model.ExpServicio;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author joel.sandoval
 */
//@Service
@Repository
public interface ExpServicioRepository extends PagingAndSortingRepository<ExpServicio, Integer> {
    
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "insert into operacion.exp_servicio(servicio, documento)\n"
            + "select :servicio as servicio, d.id as documento from operacion.exp_cat_documentos d \n"
            + "inner join operacion.exp_cat_documentos_servicios dt on dt.documento  = d.id  \n"
            + "where dt.servicio = :estudio and (d.persona = :persona or d.persona = 3) order by orden", nativeQuery = true)
    public void creaServicioExpediente(@Param("servicio") Integer servicio, @Param("estudio") Integer estudio, @Param("persona") Integer persona);

}
