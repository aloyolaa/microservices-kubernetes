package org.aloyolaa.springcloud.msvc.users.client;

import org.aloyolaa.springcloud.msvc.users.domain.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-courses", url = "${msvc.courses.host}:${msvc.courses.port}")
public interface CourseClientRest {
    @DeleteMapping("/delete-user/{userId}")
    ResponseDto<Boolean> deleteByUserId(@PathVariable Long userId);
}
