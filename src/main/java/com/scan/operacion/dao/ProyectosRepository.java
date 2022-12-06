package com.scan.operacion.dao;

import com.scan.operacion.model.Proyectos;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface ProyectosRepository extends CrudRepository<Proyectos, Integer> {

    @Query(value = "select p.* from operacion.proyectos p where p.estatus > 0", nativeQuery = true)
    public List<Proyectos> dameProyectosActivos();
}
