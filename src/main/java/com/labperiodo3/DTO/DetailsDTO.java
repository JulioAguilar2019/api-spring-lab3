package com.labperiodo3.DTO;

import lombok.Data;

@Data
public class DetailsDTO {

    private Long id;
    private String product;
    private Integer quantity;
    private Double subtotal;
}
