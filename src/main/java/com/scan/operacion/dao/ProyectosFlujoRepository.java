package com.scan.operacion.dao;

import com.scan.operacion.model.ProyectosFlujo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface ProyectosFlujoRepository extends CrudRepository<ProyectosFlujo, Integer> {

//    @Query(value = "select f.* from operacion.proyectos p where p.estatus > 0", nativeQuery = true)
//    public List<ProyectosFlujo> dameProyectosActivos();
}
