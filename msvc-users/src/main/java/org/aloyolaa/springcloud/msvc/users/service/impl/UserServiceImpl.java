package org.aloyolaa.springcloud.msvc.users.service.impl;

import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aloyolaa.springcloud.msvc.users.client.CourseClientRest;
import org.aloyolaa.springcloud.msvc.users.domain.entity.User;
import org.aloyolaa.springcloud.msvc.users.exception.ServiceCommunicationException;
import org.aloyolaa.springcloud.msvc.users.repository.UserRepository;
import org.aloyolaa.springcloud.msvc.users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CourseClientRest courseClientRest;

    private static final String ENTITY_NOT_FOUND_EXCEPTION_MESSAGE = "There is no user with id: ";

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION_MESSAGE + id));
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        User byId = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION_MESSAGE + id));
        byId.setId(id);
        byId.setName(user.getName());
        byId.setEmail(user.getEmail());
        byId.setPassword(user.getPassword());
        return userRepository.save(byId);
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION_MESSAGE + id);
        }

        userRepository.deleteById(id);

        try {
            courseClientRest.deleteByUserId(id);
        } catch (FeignException e) {
            throw new ServiceCommunicationException("course");
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllById(List<Long> ids) {
        return userRepository.findAllById(ids);
    }
}
