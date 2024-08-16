package org.aloyolaa.springcloud.msvc.users.model.dto;

public record ErrorResponseDto<T>(
        String title,
        T message
) {
}
