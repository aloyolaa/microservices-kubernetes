package org.aloyolaa.springcloud.msvc.courses.model.dto;

public record ResponseDto<T>(
        T response,
        Boolean isSuccessfully
) {
}
