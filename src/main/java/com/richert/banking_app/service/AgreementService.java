package com.richert.banking_app.service;

import com.richert.banking_app.dto.AgreementDTO;

import java.util.List;

public interface AgreementService {

    List<AgreementDTO> getAgreementsByClientsId(String id);

    List<AgreementDTO> getAllAgreements();
}