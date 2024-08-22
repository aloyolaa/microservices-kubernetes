package org.aloyolaa.springcloud.msvc.users.domain.dto;

public record ErrorResponseDto<T>(
        String title,
        T message
) {
}
