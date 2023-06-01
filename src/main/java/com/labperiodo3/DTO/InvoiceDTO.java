package com.labperiodo3.DTO;

import com.labperiodo3.Entities.DetailsEntity;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Data
public class InvoiceDTO {

    private Long id;
    private String name;
    private String dui;
    private String address;
    private Date date;
    private Double total;

    @Getter
    private List<DetailsEntity> details;
}
