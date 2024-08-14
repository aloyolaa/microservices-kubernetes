package org.aloyolaa.springcloud.msvc.courses.repository;

import org.aloyolaa.springcloud.msvc.courses.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}