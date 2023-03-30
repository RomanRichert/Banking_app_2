package com.richert.banking_app.mapper;

import com.richert.banking_app.dto.ClientRequestDTO;
import com.richert.banking_app.dto.ClientResponseDTO;
import com.richert.banking_app.entity.Client;
import com.richert.banking_app.repository.ManagerRepository;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring",
        uses = {AccountMapper.class, ManagerMapper.class},
        injectionStrategy = CONSTRUCTOR,
        imports = {Timestamp.class})
public interface ClientMapper {

    @Mapping(source = "manager.id", target = "manager")
    ClientResponseDTO toDTO(Client client);

    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))")
    Client toEntity(ClientRequestDTO clientRequestDTO, @Context ManagerRepository managerRepository);

    List<ClientResponseDTO> clientsToClientDTOs(List<Client> clients);

    @AfterMapping
    default void getManagerForClient(@MappingTarget Client target, ClientRequestDTO clientRequestDTO, @Context ManagerRepository managerRepository) {
        target.setManager(managerRepository.findById(clientRequestDTO.getManager()).orElse(null));
    }
}