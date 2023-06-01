package com.labperiodo3.Services;

import com.labperiodo3.DTO.DetailsDTO;
import com.labperiodo3.Entities.DetailsEntity;
import com.labperiodo3.Entities.InvoiceEntity;
import com.labperiodo3.Repositories.DetailsRepository;
import com.labperiodo3.Repositories.InvoiceRepository;
import com.labperiodo3.exceptions.InvoicesAppException;
import com.labperiodo3.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsServiceImp implements DetailsService {

    @Autowired
    private DetailsRepository detailsRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DetailsDTO createDetails(Long invoiceId, DetailsDTO detailsDTO) {
        DetailsEntity detailsEntity = convertDTOToEntity(detailsDTO);
        InvoiceEntity invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", invoiceId));

        detailsEntity.setInvoice(invoice);
        DetailsEntity newDetails = detailsRepository.save(detailsEntity);

        return convertEntityToDTO(newDetails);
    }

    @Override
    public List<DetailsDTO> getAllDetails(Long invoiceId) {
        List<DetailsEntity> details = detailsRepository.findByInvoiceId(invoiceId);
        return details.stream().map(detail -> convertEntityToDTO(detail)).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public DetailsDTO getDetailsById(Long invoiceId, Long detailsId) {

        DetailsEntity details = validateDetails(invoiceId, detailsId);

        return convertEntityToDTO(details);

    }

    @Override
    public DetailsDTO updateDetails(Long invoiceId, Long detailsId, DetailsDTO details) {

        DetailsEntity newDetails = validateDetails(invoiceId, detailsId);

        newDetails.setQuantity(details.getQuantity());
        newDetails.setProduct(details.getProduct());
        newDetails.setSubtotal(details.getSubtotal());

        DetailsEntity updatedDetails = detailsRepository.save(newDetails);

        return convertEntityToDTO(updatedDetails);
    }

    @Override
    public void deleteDetails(Long invoiceId, Long detailsId) {
        DetailsEntity details = validateDetails(invoiceId, detailsId);
        detailsRepository.delete(details);
    }

    private DetailsEntity validateDetails(Long invoiceId, Long idDetails) {
        InvoiceEntity invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", invoiceId));

        DetailsEntity details = detailsRepository.findById(idDetails)
                .orElseThrow(() -> new ResourceNotFoundException("Details", "id", idDetails));

        if (details.getInvoice().getId() != invoice.getId()) {
            throw new InvoicesAppException(HttpStatus.BAD_REQUEST, "Details with id " + idDetails + " does not belong to invoice with id " + invoiceId);
        }

        return details;
    }

    private DetailsDTO convertEntityToDTO(DetailsEntity detailsEntity) {
        DetailsDTO detailsDTO = modelMapper.map(detailsEntity, DetailsDTO.class);
        return detailsDTO;
    }

    private DetailsEntity convertDTOToEntity(DetailsDTO detailsDTO) {
        DetailsEntity detailsEntity = modelMapper.map(detailsDTO, DetailsEntity.class);
        return detailsEntity;
    }

}
