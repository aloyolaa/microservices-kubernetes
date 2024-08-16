package org.aloyolaa.springcloud.msvc.users.model.dto;

public record ResponseDto<T>(
        T response,
        Boolean isSuccessfully
) {
}
