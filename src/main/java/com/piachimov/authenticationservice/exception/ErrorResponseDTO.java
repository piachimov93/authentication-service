package com.piachimov.authenticationservice.exception;

public record ErrorResponseDTO(String errorMessage, int errorCode) {
}
