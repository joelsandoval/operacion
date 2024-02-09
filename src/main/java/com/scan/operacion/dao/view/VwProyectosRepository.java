package com.scan.operacion.dao.view;

import com.scan.operacion.model.view.VwProyectos;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface VwProyectosRepository extends CrudRepository<VwProyectos, Integer> {

    @Query(value = "select p.* from operacion.vw_proyectos p where p.id = :id", nativeQuery = true)
    public Optional<VwProyectos> damePorId(@Param("id") Integer id);
    
    @Query(value = "select p.* from operacion.vw_proyectos p where p.estatus_id > 0 order by p.id desc", nativeQuery = true)
    public List<VwProyectos> dameProyectosActivos();
    
    
      
}
