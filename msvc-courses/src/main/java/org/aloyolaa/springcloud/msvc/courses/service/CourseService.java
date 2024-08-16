package org.aloyolaa.springcloud.msvc.courses.service;

import org.aloyolaa.springcloud.msvc.courses.model.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAll();

    Course getById(Long id);

    Course save(Course course);

    Course update(Long id, Course course);

    Boolean delete(Long id);

    Boolean existsByName(String name);
}
