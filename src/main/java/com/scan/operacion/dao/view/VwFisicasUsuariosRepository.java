package com.scan.operacion.dao.view;

import com.scan.operacion.model.view.VwFisicasUsuarios;
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
public interface VwFisicasUsuariosRepository extends CrudRepository<VwFisicasUsuarios, Integer> {

    @Query(value = "select f.* from operacion.vw_fisicas_usuarios f where f.moral = :moral", nativeQuery = true)
    public List<VwFisicasUsuarios> dameUsuarios(@Param("moral") Integer moral);
    
      
}
