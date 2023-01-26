package com.scan.operacion.dao.view;

import com.scan.operacion.model.view.VwCatActividadesServicios;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
//@Service
@Repository
public interface VwCatActividadesServiciosRepository extends PagingAndSortingRepository<VwCatActividadesServicios, Integer> {

    List<VwCatActividadesServicios> findByServicio(Integer servicio);

}
