package com.uvaliavaty.userservice.service;

import com.uvaliavaty.userservice.model.User;
import com.uvaliavaty.userservice.repository.UserRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        user.setAmountOfPosts(0);
        try {
            logToFile(user);
        } catch (IOException e) {
            LOGGER.error("Error working logging to file", e);
        }
        return userRepository.save(user);
    }

    private void logToFile(User user) throws IOException {
        Path usersFile = Path.of("users-data", "users.txt");
        if (!Files.exists(usersFile)) {
            Files.createFile(usersFile);
            LOGGER.info("Created file = {}", usersFile.toAbsolutePath());
        }
        Files.writeString(usersFile, "User created: " + user.toString() + "\n", StandardOpenOption.APPEND);
        LOGGER.info("File's content: {}", Files.readAllLines(usersFile));
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
