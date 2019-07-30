package com.apirest.Services;

import com.apirest.Exceptions.ResourceNotFoundException;
import com.apirest.Models.User;
import com.apirest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Integer id) {
        Optional<User> opt;
        opt = userRepository.findById(id);
        return opt.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public User update(Integer id, User user) {
        User user1 = findById(id);
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setRole(user.getRole());
        return save(user1);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
