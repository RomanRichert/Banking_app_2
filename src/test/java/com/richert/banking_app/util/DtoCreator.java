package com.richert.banking_app.util;

import com.richert.banking_app.dto.ClientResponseDTO;
import com.richert.banking_app.entity.Client;
import lombok.experimental.UtilityClass;

import static com.richert.banking_app.util.EntityCreator.getClient;

@UtilityClass
public class DtoCreator {

    public static ClientResponseDTO getClientResponseDTO(){
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
                client.getCreatedAt().toString(),
                client.getManager()
        );
    }
}