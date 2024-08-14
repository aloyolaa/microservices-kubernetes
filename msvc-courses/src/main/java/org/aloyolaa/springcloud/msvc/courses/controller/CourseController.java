package org.aloyolaa.springcloud.msvc.courses.controller;

import lombok.RequiredArgsConstructor;
import org.aloyolaa.springcloud.msvc.courses.model.entity.Course;
import org.aloyolaa.springcloud.msvc.courses.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/")
    public ResponseEntity<List<Course>> getAll() {
        return new ResponseEntity<>(courseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Course> save(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.save(course), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
        return new ResponseEntity<>(courseService.update(id, course), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.delete(id), HttpStatus.OK);
    }
}
