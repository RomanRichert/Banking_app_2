package com.richert.banking_app.util;

import com.richert.banking_app.dto.AgreementDTO;
import com.richert.banking_app.dto.ClientRequestDTO;
import com.richert.banking_app.dto.ClientResponseDTO;
import com.richert.banking_app.entity.Agreement;
import com.richert.banking_app.entity.Client;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

import static com.richert.banking_app.util.EntityCreator.getAgreement;
import static com.richert.banking_app.util.EntityCreator.getClient;

@UtilityClass
public class DtoCreator {

    public static ClientResponseDTO getClientResponseDTO() {
        Client client = getClient();
        return new ClientResponseDTO(
                client.getId(),
                client.getStatus().toString(),
                client.getTaxCode(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getAddress(),
                client.getPhone(),
                null,
                String.valueOf(client.getManager().getId()),
                client.getAccounts().stream()
                        .map(Object::toString)
                        .collect(Collectors.toSet())
        );
    }

    public static AgreementDTO getAgreementDTO() {
        Agreement agreement = getAgreement();
        return new AgreementDTO(
                agreement.getId(),
                agreement.getStatus().toString(),
                agreement.getInterestRate().toString(),
                null,
                null,
                agreement.getAccount().getId(),
                agreement.getProduct().getName()
        );
    }

    public static ClientRequestDTO getClientRequestDTO() {
        Client client = getClient();
        return new ClientRequestDTO(
                client.getTaxCode(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getAddress(),
                client.getPhone(),
                client.getManager().getId()
        );
    }
}