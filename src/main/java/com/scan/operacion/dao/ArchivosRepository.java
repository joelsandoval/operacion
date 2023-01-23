package com.scan.operacion.dao;

import com.scan.operacion.model.Archivos;
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
public interface ArchivosRepository extends PagingAndSortingRepository<Archivos, Integer> {

    Optional<Archivos> findById(Integer id);
    
    @Query(value = "select a.* from operacion.archivos a \n"
            + "inner join operacion.exp_servicio_archivos eta on eta.archivo = a.id\n"
            + "where eta.expediente = :expediente", nativeQuery = true)
    List<Archivos> dameArchivosExpediente(@Param("expediente") Integer expediente);

    @Query(value = "select a.* from operacion.archivos a \n"
            + "inner join operacion.exp_servicio_archivos eta on eta.archivo = a.id\n"
            + "inner join operacion.exp_servicio et on eta.expediente = et.id \n"
            + "inner join operacion.exp_cat_documentos ecd on ecd.id = et.documento \n"
            + "where et.servicio = :servicio and ecd.tipo = :tipo", nativeQuery = true)
    List<Archivos> dameArchivosExpedienteTT(@Param("servicio") Integer servicio, @Param("tipo") Integer tipo);
}
