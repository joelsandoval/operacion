package com.scan.operacion.dao.view;

import com.scan.operacion.model.view.VwExpedienteServicioCat;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
//@Service
@Repository
public interface VwExpedienteServicioCatRepository extends PagingAndSortingRepository<VwExpedienteServicioCat, Integer> {

    List<VwExpedienteServicioCat> findByServicioOrderById(Integer servicio);

}
