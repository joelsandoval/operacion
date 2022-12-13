package com.scan.operacion.dao;

import com.scan.operacion.model.CatServiciosCategoria;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface CatServiciosCategoriaRepository extends PagingAndSortingRepository<CatServiciosCategoria, Integer> {

    Optional<CatServiciosCategoria> findById(Integer id);
    
    @Query(value = "select c.* from operacion.cat_servicios_categoria c order by c.categoria asc", nativeQuery = true)
    public List<CatServiciosCategoria> dameCategorias();
    
}
