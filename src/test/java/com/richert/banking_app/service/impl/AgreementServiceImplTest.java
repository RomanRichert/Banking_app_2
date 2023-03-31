package com.richert.banking_app.service.impl;

import com.richert.banking_app.dto.AgreementDTO;
import com.richert.banking_app.entity.Agreement;
import com.richert.banking_app.mapper.AgreementMapper;
import com.richert.banking_app.mapper.AgreementMapperImpl;
import com.richert.banking_app.repository.AgreementRepository;
import com.richert.banking_app.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.richert.banking_app.util.DtoCreator.getAgreementDTO;
import static com.richert.banking_app.util.EntityCreator.getAgreement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for AgreementServiceImpl")
class AgreementServiceImplTest {

    @InjectMocks
    private AgreementServiceImpl agreementService;

    @Spy
    private AgreementMapper agreementMapper = new AgreementMapperImpl();

    @Mock
    private AgreementRepository agreementRepository;

    @Test
    @DisplayName("Tests getting requested agreements")
    void getAgreementsByClientsId() {
        List<AgreementDTO> response = List.of(getAgreementDTO());
        List<Agreement> agreements = List.of(getAgreement());

        when(agreementRepository.findByAccountClientId(any())).thenReturn(agreements);

        assertEquals(response, agreementService.getAgreementsByClientsId(any()), "Lists should be equals");

        verify(agreementRepository).findByAccountClientId(any());
    }

    @Test
    @DisplayName("Tests getting all agreements")
    void getAllAgreements() {
        when(agreementRepository.findAll()).thenReturn(List.of(getAgreement()));

        assertEquals(List.of(getAgreementDTO()), agreementService.getAllAgreements());

        verify(agreementRepository).findAll();
    }
}