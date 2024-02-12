package com.scan.operacion.dao.view;

import com.scan.operacion.model.view.VwActividades;
import com.scan.operacion.model.view.VwProyectosServiciosActividades;
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
public interface VwActividadesRepository extends PagingAndSortingRepository<VwActividades, Integer> {

    @Query(value = "select a.* from operacion.vw_actividades a where a.proyecto = :proyecto order by a.fecha", nativeQuery = true)
    public List<VwActividades> dameActividades(@Param("proyecto") Integer proyecto);

    @Query(value = "select a.* from operacion.vw_actividades a where \n"
            + "a.responsable_id = :usuario and a.terminado = false order by a.vencimiento asc", nativeQuery = true)
    public List<VwActividades> damePendientesUsuario(@Param("usuario") Integer usuario);

}
