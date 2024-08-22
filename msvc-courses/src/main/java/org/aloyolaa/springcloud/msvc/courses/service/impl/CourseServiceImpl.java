package org.aloyolaa.springcloud.msvc.courses.service.impl;

import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aloyolaa.springcloud.msvc.courses.client.UserClientRest;
import org.aloyolaa.springcloud.msvc.courses.domain.dto.ErrorResponseDto;
import org.aloyolaa.springcloud.msvc.courses.domain.dto.ResponseDto;
import org.aloyolaa.springcloud.msvc.courses.domain.entity.Course;
import org.aloyolaa.springcloud.msvc.courses.domain.entity.CourseUser;
import org.aloyolaa.springcloud.msvc.courses.domain.model.User;
import org.aloyolaa.springcloud.msvc.courses.exception.ServiceCommunicationException;
import org.aloyolaa.springcloud.msvc.courses.repository.CourseRepository;
import org.aloyolaa.springcloud.msvc.courses.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserClientRest userClientRest;

    private static final String ENTITY_NOT_FOUND_EXCEPTION_MESSAGE = "There is no course with id: ";

    @Override
    @Transactional(readOnly = true)
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Course getById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION_MESSAGE + id));

        if (!course.getCourseUsers().isEmpty()) {
            List<Long> ids = course.getCourseUsers().stream().map(CourseUser::getUserId).toList();

            try {
                ResponseDto<List<User>> listResponseDto = userClientRest.getAllById(ids);
                course.setUsers(listResponseDto.response());
            } catch (FeignException e) {
                throw new ServiceCommunicationException("user");
            }
        }

        return course;
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public Course update(Long id, Course course) {
        Course byId = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION_MESSAGE + id));
        byId.setId(id);
        byId.setName(course.getName());
        return courseRepository.save(byId);
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION_MESSAGE + id);
        }

        courseRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsByName(String name) {
        return courseRepository.existsByName(name);
    }

    @Override
    @Transactional
    public User assignUser(Long userId, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION_MESSAGE + courseId));
        ResponseDto<User> byId;

        try {
            byId = userClientRest.getById(userId);
        } catch (FeignException e) {
            throw new ServiceCommunicationException("user");
        }

        if (Boolean.FALSE.equals(byId.isSuccessfully())) {
            throw new EntityNotFoundException();
        }

        User userMsvc = byId.response();
        CourseUser courseUser = new CourseUser();
        courseUser.setUserId(userMsvc.getId());
        course.addCourseUser(courseUser);
        courseRepository.save(course);
        return userMsvc;
    }

    @Override
    @Transactional
    public User createUser(User user, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION_MESSAGE + courseId));
        ResponseDto<User> byId;

        try {
            byId = userClientRest.save(user);
        } catch (FeignException e) {
            throw new ServiceCommunicationException("user");
        }

        if (Boolean.FALSE.equals(byId.isSuccessfully())) {
            throw new EntityNotFoundException();
        }

        User userMsvc = byId.response();
        CourseUser courseUser = new CourseUser();
        courseUser.setUserId(userMsvc.getId());
        course.addCourseUser(courseUser);
        courseRepository.save(course);
        return userMsvc;
    }

    @Override
    @Transactional
    public User removeUser(Long userId, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION_MESSAGE + courseId));
        ResponseDto<User> byId;

        try {
            byId = userClientRest.getById(userId);
        } catch (FeignException e) {
            throw new ServiceCommunicationException("user");
        }

        if (Boolean.FALSE.equals(byId.isSuccessfully())) {
            throw new EntityNotFoundException();
        }

        User userMsvc = byId.response();
        CourseUser courseUser = new CourseUser();
        courseUser.setUserId(userMsvc.getId());
        course.removeCourseUser(courseUser);
        courseRepository.save(course);
        return userMsvc;
    }
}
