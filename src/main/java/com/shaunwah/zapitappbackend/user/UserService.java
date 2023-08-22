package com.shaunwah.zapitappbackend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public Boolean deleteUser(long userId) {
        return userRepository.deleteById(userId) > 0;
    }

//    public List<User> getUsers(int limit, int offset) {
//        return userRepository.getUsers(limit, offset);
//    }
//
//    public Optional<User> getUserById(int id) {
//        try {
//            return Optional.of(userRepository.getUserById(id));
//        } catch (Exception e) {
//            log.severe(ex.getMessage());
//            return Optional.empty();
//        }
//    }
//
//    public Optional<User> createUser(User user) {
//        try {
//            return Optional.of(userRepository.createUser(user));
//        } catch (Exception e) {
//            log.severe(ex.getMessage());
//            return Optional.empty();
//        }
//    }
//
//    public Boolean updateUser(User user) {
//        return userRepository.updateUser(user);
//    }
//
//    public Boolean deleteUser(int id) {
//        return userRepository.deleteUser(id);
//    }
}
