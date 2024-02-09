package com.scan.operacion.dao;

import com.scan.operacion.model.ProyectosEquipo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
@Repository
public interface ProyectosEquipoRepository extends CrudRepository<ProyectosEquipo, Integer> {


}
