package org.aloyolaa.springcloud.msvc.courses.service.impl;

import lombok.RequiredArgsConstructor;
import org.aloyolaa.springcloud.msvc.courses.model.entity.Course;
import org.aloyolaa.springcloud.msvc.courses.repository.CourseRepository;
import org.aloyolaa.springcloud.msvc.courses.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public Course update(Long id, Course course) {
        Course byId = courseRepository.findById(id).orElseThrow(NoSuchElementException::new);
        byId.setId(id);
        byId.setName(course.getName());
        return courseRepository.save(byId);
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsByName(String name) {
        return courseRepository.existsByName(name);
    }
}
