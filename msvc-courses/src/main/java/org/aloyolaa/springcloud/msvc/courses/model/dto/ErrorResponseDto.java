package org.aloyolaa.springcloud.msvc.courses.model.dto;

public record ErrorResponseDto<T>(
        String title,
        T message
) {
}
