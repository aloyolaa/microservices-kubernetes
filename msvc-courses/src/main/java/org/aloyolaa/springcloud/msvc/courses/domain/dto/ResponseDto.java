package org.aloyolaa.springcloud.msvc.courses.domain.dto;

public record ResponseDto<T>(
        T response,
        Boolean isSuccessfully
) {
}
