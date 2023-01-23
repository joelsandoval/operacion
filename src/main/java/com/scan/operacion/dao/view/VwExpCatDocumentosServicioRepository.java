package com.scan.operacion.dao.view;

import com.scan.operacion.model.view.VwExpCatDocumentosServicio;
import com.scan.operacion.model.view.VwExpedienteServicioCat;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
//@Service
@Repository
public interface VwExpCatDocumentosServicioRepository extends PagingAndSortingRepository<VwExpCatDocumentosServicio, Integer> {

    @Query(value = "select * from operacion.vw_exp_cat_documentos_servicio ds \n"
            + "where ds.servicio = :servicio order by ds.catego_id, ds.documento", nativeQuery = true)
    List<VwExpCatDocumentosServicio> damePorServicio(@Param("servicio") Integer servicio);

}
