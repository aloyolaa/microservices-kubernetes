package org.aloyolaa.springcloud.msvc.users.service;

import org.aloyolaa.springcloud.msvc.users.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getById(Long id);

    User save(User user);

    User update(Long id, User user);

    Boolean delete(Long id);
}
