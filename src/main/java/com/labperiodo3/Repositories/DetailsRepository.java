package com.labperiodo3.Repositories;

import com.labperiodo3.Entities.DetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailsRepository extends JpaRepository<DetailsEntity, Long> {

    List<DetailsEntity> findByInvoiceId(Long idInvoice);

}
