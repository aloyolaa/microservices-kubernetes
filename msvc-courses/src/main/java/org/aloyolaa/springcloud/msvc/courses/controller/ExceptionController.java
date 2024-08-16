package org.aloyolaa.springcloud.msvc.courses.controller;

import lombok.extern.slf4j.Slf4j;
import org.aloyolaa.springcloud.msvc.courses.model.dto.ErrorResponseDto;
import org.aloyolaa.springcloud.msvc.courses.model.dto.ResponseDto;
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

        String errorMessage = "MethodArgumentNotValidException: Datos no válidos.";

        log.error(errorMessage);

        ErrorResponseDto<Map<String, String>> errorResponse = new ErrorResponseDto<>("Error al Guardar Datos", errors);

        return new ResponseEntity<>(
                new ResponseDto<>(
                        errorResponse,
                        false)
                , HttpStatus.BAD_REQUEST);
    }
}
