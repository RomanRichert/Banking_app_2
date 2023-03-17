package com.richert.banking_app.mapper;

import com.richert.banking_app.dto.ClientRequestDTO;
import com.richert.banking_app.dto.ClientResponseDTO;
import com.richert.banking_app.entity.Client;
import com.richert.banking_app.repository.ManagerRepository;
import com.richert.banking_app.service.impl.ManagerServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring",
        uses = {AccountMapper.class},
        injectionStrategy = CONSTRUCTOR,
        imports = {Timestamp.class, ManagerRepository.class, ManagerServiceImpl.class})
public interface ClientMapper {

    @Mapping(source = "manager.id", target = "manager")
    ClientResponseDTO toDTO(Client client);

    @Mapping(target = "createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))")
    @Mapping(target = "manager", expression = "java(ManagerServiceImpl.findManager(clientRequestDTO))")
    Client toEntity(ClientRequestDTO clientRequestDTO);

    List<ClientResponseDTO> clientsToClientDTOs(List<Client> clients);
}