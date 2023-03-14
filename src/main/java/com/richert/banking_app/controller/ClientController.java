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

    @ResponseStatus(OK)
    @GetMapping("/status/{status}")
    @Operation(summary = "Request for all clients by status", description = "Getting all clients with given status")
    @ApiResponse(responseCode = "200", description = "Successfully returned list of clients", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientResponseDTO.class)))
    })
    public List<ClientResponseDTO> getAllClientsByStatus(@PathVariable String status) {
        return clientService.getAllClientsByStatus(status);
    }
}