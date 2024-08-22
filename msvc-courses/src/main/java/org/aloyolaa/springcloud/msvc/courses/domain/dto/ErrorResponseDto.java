package org.aloyolaa.springcloud.msvc.courses.domain.dto;

public record ErrorResponseDto<T>(
        String title,
        T message
) {
}
