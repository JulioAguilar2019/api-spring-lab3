package com.labperiodo3.Controllers;


import com.labperiodo3.DTO.DetailsDTO;
import com.labperiodo3.Services.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class DetailsController {

    @Autowired
    private DetailsService detailsService;


    @GetMapping("/invoices/{idInvoice}/details")
    public List<DetailsDTO> findDetailsByidInvoice(@PathVariable(value = "idInvoice") Long idInvoice) {
        return detailsService.getAllDetails(idInvoice);
    }

    @GetMapping("/invoices/{idInvoice}/details/{idDetails}")
    public ResponseEntity<DetailsDTO> findDetailsById(@PathVariable(value = "idInvoice") long idInvoice, @PathVariable(value = "idDetails") Long idDetails) {
        return ResponseEntity.ok(detailsService.getDetailsById(idInvoice, idDetails));
    }

    @PostMapping("/invoices/{idInvoice}/details")
    public ResponseEntity<DetailsDTO> createDetails(@PathVariable(value = "idInvoice") long idInvoice, @RequestBody DetailsDTO detailsDTO) {
        return new ResponseEntity<>(detailsService.createDetails(idInvoice, detailsDTO), HttpStatus.CREATED);
    }

    @PutMapping("/invoices/{idInvoice}/details/{idDetails}")
    public ResponseEntity<DetailsDTO> updateDetails(@PathVariable(value = "idInvoice") long idInvoice, @PathVariable(value = "idDetails") Long idDetails, @RequestBody DetailsDTO detailsDTO) {
        return new ResponseEntity<>(detailsService.updateDetails(idInvoice, idDetails, detailsDTO), HttpStatus.OK);
    }

    @DeleteMapping("/invoices/{idInvoice}/details/{idDetails}")
    public ResponseEntity<String> deleteDetails(@PathVariable(value = "idInvoice") long idInvoice, @PathVariable(value = "idDetails") Long idDetails) {
        detailsService.deleteDetails(idInvoice, idDetails);
        return new ResponseEntity<>("Details deleted successfully", HttpStatus.OK);
    }
}
