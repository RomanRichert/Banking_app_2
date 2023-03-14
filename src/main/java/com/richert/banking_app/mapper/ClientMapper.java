package com.richert.banking_app.mapper;

import com.richert.banking_app.dto.ClientResponseDTO;
import com.richert.banking_app.entity.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientResponseDTO toDTO(Client client);

    List<ClientResponseDTO> clientsToClientDTOs(List<Client> clients);
}