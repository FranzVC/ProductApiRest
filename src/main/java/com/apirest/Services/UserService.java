package com.apirest.Services;

import com.apirest.Models.User;

public interface UserService {
    User save(User user);
    void deleteById(Integer id );
    User findById(Integer id);
    User update(Integer id, User user);
    User findByUsername(String username);
    Iterable<User> findAll();

}
