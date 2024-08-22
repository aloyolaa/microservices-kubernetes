package org.aloyolaa.springcloud.msvc.courses.repository;

import org.aloyolaa.springcloud.msvc.courses.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByName(String name);
}