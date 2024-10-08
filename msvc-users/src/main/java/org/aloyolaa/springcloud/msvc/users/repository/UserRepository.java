package org.aloyolaa.springcloud.msvc.users.repository;

import org.aloyolaa.springcloud.msvc.users.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}