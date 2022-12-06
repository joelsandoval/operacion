package com.scan.operacion.dao;

import com.scan.operacion.view.VwProyectos;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface VwProyectosRepository extends CrudRepository<VwProyectos, Integer> {

    @Query(value = "select p.* from operacion.vw_proyectos p where p.estatus_id > 0", nativeQuery = true)
    public List<VwProyectos> dameProyectosActivos();
    
    
      
}
