package org.kaiLearn.springboot_restful_services.service.impl;

import lombok.AllArgsConstructor;
import org.kaiLearn.springboot_restful_services.entity.User;
import org.kaiLearn.springboot_restful_services.repository.UserRepository;
import org.kaiLearn.springboot_restful_services.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
@Override
    public User createUser(User user){
    return userRepository.save(user);
}

    @Override
    public User getUserById(Long userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }
}
