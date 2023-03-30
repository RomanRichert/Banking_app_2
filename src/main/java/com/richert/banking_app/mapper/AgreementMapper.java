package com.richert.banking_app.mapper;

import com.richert.banking_app.dto.AgreementDTO;
import com.richert.banking_app.entity.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgreementMapper {

    @Mapping(source = "account.id", target = "account")
    @Mapping(source = "product.name", target = "product")
    AgreementDTO toDTO(Agreement agreement);

    List<AgreementDTO> agreementsToAgreementDTOs(List<Agreement> agreements);
}