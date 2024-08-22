package org.aloyolaa.springcloud.msvc.courses.client;

import org.aloyolaa.springcloud.msvc.courses.domain.dto.ResponseDto;
import org.aloyolaa.springcloud.msvc.courses.domain.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-users", url = "localhost:8001")
public interface UserClientRest {
    @GetMapping("/{id}")
    ResponseDto<User> getById(@PathVariable  Long id);

    @PostMapping("/save")
    ResponseDto<User> save(@RequestBody User user);

    @GetMapping("/by-course")
    ResponseDto<List<User>> getAllById(@RequestParam List<Long> ids);
}
