package com.labperiodo3.DTO;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceResponse {
    private List<InvoiceDTO> content;
    private int page;
    private int size;
    private Long totalElements;
    private int totalPages;
    private boolean lastPage;
}
