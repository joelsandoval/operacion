package com.scan.operacion.dao.view;

import com.scan.operacion.model.view.VwExpedienteServicio;
import java.util.HashMap;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author joel.sandoval
 */
//@Service
@Repository
public interface VwExpedienteServicioRepository extends PagingAndSortingRepository<VwExpedienteServicio, Integer> {

    List<VwExpedienteServicio> findByServicio(Integer servicio);

    @Query(value = "select * from operacion.vw_expediente_servicio vet \n"
            + "where vet.servicio = :servicio and vet.tipo = :tipo order by vet.orden asc", nativeQuery = true)
    List<VwExpedienteServicio> damePorTramiteTipo(@Param("servicio") Integer servicio, @Param("tipo") Integer tipo);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update operacion.exp_servicio set presenta = :presenta where id = :expediente", nativeQuery = true)
    public void updPresenta(@Param("expediente") Integer expediente, @Param("presenta") Boolean presenta);

    @Query(value = "select * from operacion.vw_expediente_servicio vet \n"
            + "where vet.servicio = :servicio order by vet.tipo, vet.orden asc", nativeQuery = true)
    List<VwExpedienteServicio> damePorServicio(@Param("servicio") Integer servicio);

    @Query(value = "select distinct dc.id, dc.categoria, et.servicio from operacion.exp_servicio et \n"
            + "join operacion.exp_cat_documentos d on d.id = et.documento \n"
            + "join operacion.exp_cat_documentos_catego dc on d.tipo = dc.id\n"
            + "where et.servicio = :servicio order by id", nativeQuery = true)
    List<HashMap> dameCategoriasPorServicio(@Param("servicio") Integer servicio);

}
