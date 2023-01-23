package com.scan.operacion.dao;

import com.scan.operacion.model.ExpCatDocumentosCatego;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
//@Service
@Repository
public interface ExpCatDocumentosCategoRepository extends PagingAndSortingRepository<ExpCatDocumentosCatego, Integer> {

   
    @Query(value="select * from operacion.exp_cat_documentos_catego d order by d.categoria", nativeQuery=true)
    List<ExpCatDocumentosCatego> dameTodasCategorias();

}
