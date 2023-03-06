package com.richert.banking_app.controller;

import com.richert.banking_app.dto.ClientResponseDTO;
import com.richert.banking_app.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
@Tag(name = "Controller for managing clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping()
    @ResponseStatus(OK)
    @Operation(summary = "Request for all clients", description = "Getting all clients with given status. Getting all clients if no params entered")
    @ApiResponse(responseCode = "200", description = "Successfully returned list of clients", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientResponseDTO.class)))
    })
    public List<ClientResponseDTO> getAllClients(@RequestParam(required = false) String status) {
        return clientService.getAllClients(status);
    }
}