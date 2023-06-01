package com.labperiodo3.Services;

import com.labperiodo3.DTO.DetailsDTO;

import java.util.List;

public interface DetailsService {

    DetailsDTO createDetails(Long invoiceId, DetailsDTO detailsDTO);

    List<DetailsDTO> getAllDetails(Long invoiceId);

    DetailsDTO getDetailsById(Long invoiceId, Long detailsId);

    DetailsDTO updateDetails(Long invoiceId, Long detailsId, DetailsDTO details);

    void deleteDetails(Long invoiceId, Long detailsId);


}
