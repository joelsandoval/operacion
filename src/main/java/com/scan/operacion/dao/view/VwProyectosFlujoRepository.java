package com.scan.operacion.dao.view;

import com.scan.operacion.model.view.VwProyectosFlujo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface VwProyectosFlujoRepository extends CrudRepository<VwProyectosFlujo, Integer> {

    @Query(value = "select f.* from operacion.vw_proyectos_flujo f where f.referencia = :referencia and f.nivel = :nivel", nativeQuery = true)
    public List<VwProyectosFlujo> dameProyectoFlujo(@Param("referencia") Integer referencia, @Param("nivel") Integer nivel);
    
      
}
