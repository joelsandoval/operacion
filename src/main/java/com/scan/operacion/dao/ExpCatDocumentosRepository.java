package com.scan.operacion.dao;

import com.scan.operacion.model.ExpCatDocumentos;
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
public interface ExpCatDocumentosRepository extends PagingAndSortingRepository<ExpCatDocumentos, Integer> {

    List<ExpCatDocumentos> findByTipoOrderByDocumento(Integer tipo);
    
    List<ExpCatDocumentos> findByTipoAndPersonaOrderByDocumento(Integer tipo, Integer persona);
    
    @Query(value="select * from operacion.exp_cat_documentos d where d.tipo = :tipo and (d.persona = 3 or d.persona = :persona) \n" +
        "and d.id not in (select v.id_documento from operacion.vw_expediente_servicio v where v.servicio = :servicio) order by documento", nativeQuery=true)
    List<ExpCatDocumentos> damePorTipoPersonaNoServicio(@Param("tipo") Integer tipo, @Param("persona") Integer persona, @Param("servicio") Integer tramite);

}
