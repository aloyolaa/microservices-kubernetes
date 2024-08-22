package org.aloyolaa.springcloud.msvc.courses.repository;

import org.aloyolaa.springcloud.msvc.courses.domain.entity.CourseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CourseUserRepository extends JpaRepository<CourseUser, Long> {
    @Transactional
    @Modifying
    @Query("delete from CourseUser c where c.userId = ?1")
    void deleteByUserId(Long userId);
}