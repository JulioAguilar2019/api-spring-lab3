package com.labperiodo3.Controllers;

import com.labperiodo3.DTO.InvoiceDTO;
import com.labperiodo3.DTO.InvoiceResponse;
import com.labperiodo3.Services.InvoiceService;
import com.labperiodo3.utilities.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public InvoiceResponse getAllPosts(@RequestParam(value = "page", defaultValue = AppConstants.NUMBER_OF_PAGE_DEFAULT, required = false) int page,
                                       @RequestParam(value = "size", defaultValue = AppConstants.SIZE_OF_PAGE_DEFAULT, required = false) int size,
                                       @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_DEFAULT, required = false) String sortBy,
                                       @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_DEFAULT, required = false) String sortDir) {
        return invoiceService.getAllInvoices(page, size, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getPostById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(invoiceService.getInvoiceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InvoiceDTO> saveInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return new ResponseEntity<>(invoiceService.createInvoice(invoiceDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@RequestBody InvoiceDTO invoiceDTO, @PathVariable("id") Long id) {
        return new ResponseEntity<>(invoiceService.updateInvoice(invoiceDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        invoiceService.deleteInvoice(id);
        return new ResponseEntity<>("Invoice deleted successfully", HttpStatus.OK);
    }
}
