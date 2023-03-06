package com.richert.banking_app.controller;

import com.richert.banking_app.dto.ErrorResponseDTO;
import com.richert.banking_app.exception.BadRequestException;
import com.richert.banking_app.exception.EntityNotFoundException;
import com.richert.banking_app.exception.InvalidClientStatusException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    @ApiResponse(responseCode = "404", description = "NOT FOUND", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    public ErrorResponseDTO handleEntityNotFoundException(EntityNotFoundException ex) {
        return buildErrorResponseDTO(ex, NOT_FOUND);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ApiResponse(responseCode = "400", description = "Validation Error", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    public ErrorResponseDTO handleValidationErrors(ConstraintViolationException e) {
        Map<String, List<String>> errors = e.getConstraintViolations().stream()
                .collect(groupingBy(c -> c.getPropertyPath().toString(),
                        mapping(ConstraintViolation::getMessageTemplate,
                                toList())));

        return ErrorResponseDTO.builder()
                .message("Input validation error")
                .status(BAD_REQUEST)
                .statusCode(BAD_REQUEST.value())
                .errors(errors)
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    public ErrorResponseDTO handleBadRequestException(BadRequestException ex) {
        return buildErrorResponseDTO(ex, BAD_REQUEST);
    }

    @ResponseStatus(NOT_ACCEPTABLE)
    @ExceptionHandler(InvalidClientStatusException.class)
    @ApiResponse(responseCode = "406", description = "Wrong client status entered", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    public ErrorResponseDTO handleInvalidTransactionTypeException(InvalidClientStatusException ex) {
        return buildErrorResponseDTO(ex, NOT_ACCEPTABLE);
    }

    private ErrorResponseDTO buildErrorResponseDTO(Exception ex, HttpStatus status) {
        return ErrorResponseDTO.builder()
                .status(status)
                .message(ex.getMessage())
                .statusCode(status.value())
                .errors(ex.hashCode())
                .build();
    }
}