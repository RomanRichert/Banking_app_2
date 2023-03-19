package com.richert.banking_app.service.impl;

import com.richert.banking_app.dto.AgreementDTO;
import com.richert.banking_app.mapper.AgreementMapper;
import com.richert.banking_app.repository.AgreementRepository;
import com.richert.banking_app.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;

    private final AgreementMapper agreementMapper;

    @Override
    public List<AgreementDTO> getAgreementsByClientsId(String id) {
        return agreementMapper.agreementsToAgreementDTOs(agreementRepository.findByAccountClientId(id));
    }
}