package com.richert.banking_app.mapper;

import com.richert.banking_app.dto.ClientResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.List;

import static com.richert.banking_app.util.DtoCreator.getClientResponseDTO;
import static com.richert.banking_app.util.EntityCreator.getClient;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ClientMapperTest {

    ClientMapper clientMapper = new ClientMapperImpl();

    @Test
    void toDTO() {
        int i = 0;
        Field[] fields = getClientResponseDTO().getClass().getDeclaredFields();
        Field[] functionFields = clientMapper.toDTO(getClient()).getClass().getDeclaredFields();

        while (i < fields.length) {
            if (!fields[i].getName().equals("createdAt")) {
                assertEquals(fields[i], functionFields[i], "Something went wrong by mapping Client to ClientResponseDTO in field: " + fields[i].getName());
            }
            i++;
        }
    }

    @Test
    void clientsToClientDTOs() {
        List<ClientResponseDTO> dtos = List.of(getClientResponseDTO());
        List<ClientResponseDTO> afterMethod = clientMapper.clientsToClientDTOs(List.of(getClient()));

        afterMethod.forEach(c -> dtos.forEach(d -> assertEquals(d.getId(), c.getId(), "Something went wrong by mapping List<Client> to List<ClientResponseDTO>")));
    }
}