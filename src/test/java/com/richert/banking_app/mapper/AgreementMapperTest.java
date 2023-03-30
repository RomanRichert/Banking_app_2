package com.richert.banking_app.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.richert.banking_app.util.DtoCreator.getAgreementDTO;
import static com.richert.banking_app.util.EntityCreator.getAgreement;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test class for AgreementMapper")
class AgreementMapperTest {

    private final AgreementMapper agreementMapper = new AgreementMapperImpl();

    @Test
    @DisplayName("Testing mapping of Agreement into AgreementDTO")
    void toDTO() {
        assertEquals(getAgreementDTO(), agreementMapper.toDTO(getAgreement()), "Something went wrong by mapping Agreement to AgreementDTO");
    }

    @Test
    @DisplayName("Testing mapping of List<Agreement> into List<AgreementDTO>")
    void agreementsToAgreementDTOs() {
        assertEquals(List.of(getAgreementDTO()), agreementMapper.agreementsToAgreementDTOs(List.of(getAgreement())), "Something went wrong by mapping List<Agreement> to List<AgreementDTO>");
    }
}