package com.labperiodo3.Services;

import com.labperiodo3.DTO.InvoiceDTO;
import com.labperiodo3.DTO.InvoiceResponse;
import com.labperiodo3.Entities.InvoiceEntity;
import com.labperiodo3.Repositories.InvoiceRepository;
import com.labperiodo3.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImp implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity invoice = convertDTOToEntity(invoiceDTO);

        InvoiceEntity newInvoice = invoiceRepository.save(invoice);

        return convertEntityToDTO(newInvoice);
    }

    @Override
    public InvoiceResponse getAllInvoices(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ?
                Sort.by(sortBy).ascending()
                :
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<InvoiceEntity> invoices = invoiceRepository.findAll(pageable);

        List<InvoiceEntity> listInvoices = invoices.getContent();
        List<InvoiceDTO> content = listInvoices.stream().map(invoice -> convertEntityToDTO(invoice)).collect(java.util.stream.Collectors.toList());
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setContent(content);
        invoiceResponse.setPage(invoices.getNumber());
        invoiceResponse.setSize(invoices.getSize());
        invoiceResponse.setTotalElements(invoices.getTotalElements());
        invoiceResponse.setTotalPages(invoices.getTotalPages());
        invoiceResponse.setLastPage(invoices.isLast());

        return invoiceResponse;
    }

    @Override
    public InvoiceDTO getInvoiceById(Long id) {
        InvoiceEntity invoice = invoiceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", id));

        return convertEntityToDTO(invoice);
    }

    @Override
    public InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO, Long id) {
        InvoiceEntity invoice = invoiceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", id));
        invoice.setName(invoiceDTO.getName());
        invoice.setAddress(invoiceDTO.getAddress());
        invoice.setDui(invoiceDTO.getDui());
        invoice.setDate(invoiceDTO.getDate());
        invoice.setTotal(invoiceDTO.getTotal());

        InvoiceEntity updateInvoice = invoiceRepository.save(invoice);
        return convertEntityToDTO(updateInvoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        InvoiceEntity invoice = invoiceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", id));
        invoiceRepository.delete(invoice);
    }

    private InvoiceDTO convertEntityToDTO(InvoiceEntity invoiceEntity) {
        return modelMapper.map(invoiceEntity, InvoiceDTO.class);
    }

    private InvoiceEntity convertDTOToEntity(InvoiceDTO invoiceDTO) {
        return modelMapper.map(invoiceDTO, InvoiceEntity.class);
    }
}
