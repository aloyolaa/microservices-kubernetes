package org.aloyolaa.springcloud.msvc.users.service;

import org.aloyolaa.springcloud.msvc.users.domain.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getById(Long id);

    User save(User user);

    User update(Long id, User user);

    Boolean delete(Long id);

    Boolean existsByEmail(String email);

    List<User> getAllById(List<Long> ids);
}
