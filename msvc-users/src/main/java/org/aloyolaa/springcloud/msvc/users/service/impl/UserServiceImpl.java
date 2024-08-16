package org.aloyolaa.springcloud.msvc.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.aloyolaa.springcloud.msvc.users.model.entity.User;
import org.aloyolaa.springcloud.msvc.users.repository.UserRepository;
import org.aloyolaa.springcloud.msvc.users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        User byId = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        byId.setId(id);
        byId.setName(user.getName());
        byId.setEmail(user.getEmail());
        byId.setPassword(user.getPassword());
        return userRepository.save(byId);
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
