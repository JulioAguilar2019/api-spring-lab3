package com.labperiodo3.Services;

import com.labperiodo3.DTO.InvoiceDTO;
import com.labperiodo3.DTO.InvoiceResponse;

public interface InvoiceService {

    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);

    InvoiceResponse getAllInvoices(int page, int size, String sortBy, String sortDir);

    InvoiceDTO getInvoiceById(Long id);

    InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO, Long id);

    void deleteInvoice(Long id);

}
