package com.richert.banking_app.controller;

import com.richert.banking_app.dto.AgreementDTO;
import com.richert.banking_app.service.AgreementService;
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
@RequestMapping("/agreements")
@Tag(name = "Controller for managing agreements")
public class AgreementController {

    private final AgreementService agreementService;

    @GetMapping()
    @ResponseStatus(OK)
    @ApiResponse(responseCode = "200", description = "Successfully returned list of agreements", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AgreementDTO.class)))
    })
    @Operation(summary = "Request for agreements with required clients id", description = "Getting all agreements with given clients id")
    public List<AgreementDTO> findAgreementsWhereClientIdIs(@RequestParam String clientId) {
        return agreementService.getAgreementsByClientsId(clientId);
    }

    @GetMapping("/all")
    @ResponseStatus(OK)
    @ApiResponse(responseCode = "200", description = "Successfully returned list of all agreements", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AgreementDTO.class)))
    })
    @Operation(summary = "Request for all agreements", description = "Getting all agreements")
    public List<AgreementDTO> getAllAgreements() {
        return agreementService.getAllAgreements();
    }
}