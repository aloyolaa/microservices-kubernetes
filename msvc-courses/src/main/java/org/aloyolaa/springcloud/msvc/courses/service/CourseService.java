package org.aloyolaa.springcloud.msvc.courses.service;

import org.aloyolaa.springcloud.msvc.courses.domain.entity.Course;
import org.aloyolaa.springcloud.msvc.courses.domain.model.User;

import java.util.List;

public interface CourseService {
    List<Course> getAll();

    Course getById(Long id);

    Course save(Course course);

    Course update(Long id, Course course);

    Boolean delete(Long id);

    Boolean existsByName(String name);

    User assignUser(Long userId, Long courseId);

    User createUser(User user, Long courseId);

    User removeUser(Long userId, Long courseId);
}
