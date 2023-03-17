package com.richert.banking_app.service.impl;

import com.richert.banking_app.dto.ClientRequestDTO;
import com.richert.banking_app.entity.Manager;
import com.richert.banking_app.exception.ManagerNotFoundException;
import com.richert.banking_app.repository.ManagerRepository;
import com.richert.banking_app.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    @Setter
    private static ManagerRepository managerRepository;

    public static Manager findManager(ClientRequestDTO clientRequestDTO) {
        return managerRepository.findById(clientRequestDTO.getManager()).orElseThrow(() -> new ManagerNotFoundException(clientRequestDTO.getManager()));
    }
}