package com.scan.operacion.dao;

import com.scan.operacion.model.ArchivosActividades;
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
public interface ArchivosActividadesRepository extends CrudRepository<ArchivosActividades, Integer> {


}
