package com.labperiodo3.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Invoices")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String dui;
    private Date date;
    private Double total;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice", orphanRemoval = true)
    private List<DetailsEntity> details;
}
