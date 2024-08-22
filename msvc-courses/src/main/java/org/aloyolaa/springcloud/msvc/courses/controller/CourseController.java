package org.aloyolaa.springcloud.msvc.courses.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aloyolaa.springcloud.msvc.courses.domain.dto.ResponseDto;
import org.aloyolaa.springcloud.msvc.courses.domain.entity.Course;
import org.aloyolaa.springcloud.msvc.courses.domain.model.User;
import org.aloyolaa.springcloud.msvc.courses.service.CourseService;
import org.aloyolaa.springcloud.msvc.courses.service.CourseUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CourseUserService courseUserService;

    @GetMapping("/")
    public ResponseEntity<ResponseDto<List<Course>>> getAll() {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        courseService.getAll(),
                        true
                ), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Course>> getById(@PathVariable Long id) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        courseService.getById(id),
                        true
                ), HttpStatus.OK
        );
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto<Course>> save(@Valid @RequestBody Course course) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        courseService.save(course),
                        true
                ), HttpStatus.OK
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<Course>> update(@PathVariable Long id, @Valid @RequestBody Course course) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        courseService.update(id, course),
                        true
                ), HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable Long id) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        courseService.delete(id),
                        true
                ), HttpStatus.OK
        );
    }

    @PutMapping("/assign-user/{userId}/{courseId}")
    public ResponseEntity<ResponseDto<User>> assignUser(@PathVariable Long userId, @PathVariable Long courseId) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        courseService.assignUser(userId, courseId),
                        true
                ), HttpStatus.OK
        );
    }

    @PostMapping("/create-user/{courseId}")
    public ResponseEntity<ResponseDto<User>> createUser(@RequestBody User user, @PathVariable Long courseId) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        courseService.createUser(user, courseId),
                        true
                ), HttpStatus.OK
        );
    }

    @DeleteMapping("/remove-user/{userId}/{courseId}")
    public ResponseEntity<ResponseDto<User>> removeUser(@PathVariable Long userId, @PathVariable Long courseId) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        courseService.removeUser(userId, courseId),
                        true
                ), HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<ResponseDto<Boolean>> deleteByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        courseUserService.deleteByUserId(userId),
                        true
                ), HttpStatus.OK
        );
    }
}
