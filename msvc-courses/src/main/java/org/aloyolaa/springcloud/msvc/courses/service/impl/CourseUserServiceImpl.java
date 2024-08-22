package org.aloyolaa.springcloud.msvc.courses.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aloyolaa.springcloud.msvc.courses.repository.CourseUserRepository;
import org.aloyolaa.springcloud.msvc.courses.service.CourseUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseUserServiceImpl implements CourseUserService {
    private final CourseUserRepository courseUserRepository;

    private static final String ENTITY_NOT_FOUND_EXCEPTION_MESSAGE = "In this course there is no course with id: ";

    @Override
    @Transactional
    public Boolean deleteByUserId(Long userId) {
        if (courseUserRepository.existsById(userId)) {
            throw new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION_MESSAGE + userId);
        }

        courseUserRepository.deleteByUserId(userId);
        return true;
    }
}
