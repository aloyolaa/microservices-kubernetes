package org.aloyolaa.springcloud.msvc.users.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aloyolaa.springcloud.msvc.users.domain.dto.ErrorResponseDto;
import org.aloyolaa.springcloud.msvc.users.domain.dto.ResponseDto;
import org.aloyolaa.springcloud.msvc.users.exception.ServiceCommunicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<ErrorResponseDto<Map<String, String>>>> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        String errorMessage = "MethodArgumentNotValidException: Invalid data.";

        log.error(errorMessage);

        ErrorResponseDto<Map<String, String>> errorResponse = new ErrorResponseDto<>("Error parsing data", errors);

        return new ResponseEntity<>(
                new ResponseDto<>(
                        errorResponse,
                        false)
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDto<ErrorResponseDto<String>>> entityNotFoundException(EntityNotFoundException e) {
        String errorMessage = "EntityNotFoundException: " + e.getMessage();

        log.error(errorMessage);

        ErrorResponseDto<String> errorResponse = new ErrorResponseDto<>("Error searching for data", e.getMessage());

        return new ResponseEntity<>(
                new ResponseDto<>(
                        errorResponse,
                        false)
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceCommunicationException.class)
    public ResponseEntity<ResponseDto<ErrorResponseDto<String>>> serviceCommunicationException(ServiceCommunicationException e) {
        String errorMessage = "ServiceCommunicationException: " + e.getMessage();

        log.error(errorMessage);

        ErrorResponseDto<String> errorResponse = new ErrorResponseDto<>("Error in communication between services", e.getMessage());

        return new ResponseEntity<>(
                new ResponseDto<>(
                        errorResponse,
                        false)
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
