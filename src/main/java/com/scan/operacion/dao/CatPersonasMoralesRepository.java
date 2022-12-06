package com.scan.operacion.dao;

import com.scan.operacion.model.CatPersonasMorales;
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
public interface CatPersonasMoralesRepository extends PagingAndSortingRepository<CatPersonasMorales, Integer> {
    
    @Query(value = "select c.* from operacion.cat_personas_morales c where c.tipo = :tipo order by razon asc", nativeQuery = true)
    public List<CatPersonasMorales> damePersonasPorTipo(@Param("tipo") Integer tipo);
      
}
