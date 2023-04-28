package com.uvaliavaty.userservice.service;

import com.uvaliavaty.userservice.model.User;
import com.uvaliavaty.userservice.repository.UserRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        user.setAmountOfPosts(0);
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User user) {
        user.setId(id);

        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            if (user.getUsername() == null) {
                user.setUsername(existingUser.get().getUsername());
            }
            if (user.getAmountOfPosts() == null) {
                user.setAmountOfPosts(existingUser.get().getAmountOfPosts());
            }
        } else {
            throw new NoSuchElementException("User with provided id not found");
        }

        return userRepository.save(user);
    }
}
