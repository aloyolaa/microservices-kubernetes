package org.aloyolaa.springcloud.msvc.users.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aloyolaa.springcloud.msvc.users.model.dto.ResponseDto;
import org.aloyolaa.springcloud.msvc.users.model.entity.User;
import org.aloyolaa.springcloud.msvc.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<ResponseDto<List<User>>> getAll() {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        userService.getAll(),
                        true
                ), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<User>> getById(@PathVariable Long id) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        userService.getById(id),
                        true
                ), HttpStatus.OK
        );
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto<User>> save(@Valid @RequestBody User user) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        userService.save(user),
                        true
                ), HttpStatus.OK
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<User>> update(@PathVariable Long id, @Valid @RequestBody User user) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        userService.update(id, user),
                        true
                ), HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable Long id) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        userService.delete(id),
                        true
                ), HttpStatus.OK
        );
    }
}
