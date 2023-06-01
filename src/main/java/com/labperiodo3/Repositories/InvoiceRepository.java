package com.labperiodo3.Repositories;

import com.labperiodo3.Entities.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

}
