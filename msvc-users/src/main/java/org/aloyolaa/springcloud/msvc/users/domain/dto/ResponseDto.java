package org.aloyolaa.springcloud.msvc.users.domain.dto;

public record ResponseDto<T>(
        T response,
        Boolean isSuccessfully
) {
}
