package com.richert.banking_app.controller;

import com.richert.banking_app.dto.ClientPatchingDTO;
import com.richert.banking_app.dto.ClientRequestDTO;
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

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
@Tag(name = "Controller for managing clients")
public class ClientController {

    private final ClientService clientService;

    @ResponseStatus(OK)
    @GetMapping("all/{status}")
    @Operation(summary = "Request for all clients by status", description = "Getting all clients with given status")
    @ApiResponse(responseCode = "200", description = "Successfully returned list of clients", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientResponseDTO.class)))
    })
    public List<ClientResponseDTO> getAllClientsByStatus(@PathVariable String status) {
        return clientService.getAllClientsByStatus(status);
    }

    @ResponseStatus(OK)
    @GetMapping("all/accounts")
    @Operation(summary = "Request for all clients with balance more than requested parameter", description = "Getting all clients where balance more than requested parameter")
    @ApiResponse(responseCode = "200", description = "Successfully returned list of clients", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientResponseDTO.class)))
    })
    public List<ClientResponseDTO> getAllClientsWhereBalanceMoreThan(@RequestParam double amount) {
        return clientService.getAllClientsWhereBalanceMoreThan(amount);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiResponse(responseCode = "201", description = "Successfully created a client!", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClientResponseDTO.class))
    })
    @Operation(summary = "Creating a new account", description = "Requires tax code, firstname, email, address and manager-id (if available) to create an client. Returns the created client.")
    public ClientResponseDTO createClient(@RequestBody ClientRequestDTO clientRequestDTO){
        return clientService.createClient(clientRequestDTO);
    }

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Successfully returned a specific client!", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClientResponseDTO.class))
    })
    @Operation(summary = "Request for a specific account", description = "Requires id of the client. Returns the client.")
    public ClientResponseDTO getClientById(@PathVariable String id){
        return clientService.getClientById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(OK)
    @Operation(summary = "Request for all clients", description = "Getting all clients")
    @ApiResponse(responseCode = "200", description = "Successfully returned list of clients", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientResponseDTO.class)))
    })
    public List<ClientResponseDTO> getAllClients(){
        return  clientService.getAllNotDeletedClients();
    }
    @GetMapping("/all/deleted")
    @ResponseStatus(OK)
    @Operation(summary = "Request for all deleted clients", description = "Getting all clients with ClientsStatus = REMOVED")
    @ApiResponse(responseCode = "200", description = "Successfully returned list of clients", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientResponseDTO.class)))
    })
    public List<ClientResponseDTO> getAllDeletedClients(){
        return clientService.getAllDeletedClients();
    }

    @ResponseStatus(OK)
    @PatchMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Successfully updated a specific client!", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClientResponseDTO.class))
    })
    @Operation(summary = "Request to update a specific account", description = "Requires id of the client and an object with updating fields")
    public void updateClient(@RequestBody ClientPatchingDTO clientPatchingDTO, @PathVariable String id){
        clientService.updateClient(clientPatchingDTO, id);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted client!", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClientResponseDTO.class))
    })
    @Operation(summary = "Request to delete a specific account", description = "Requires id of the client to delete the client")
    public void deleteClient(@PathVariable String id){
        clientService.deleteClient(id);
    }
}