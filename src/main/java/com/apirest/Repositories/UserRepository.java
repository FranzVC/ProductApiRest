package com.apirest.Repositories;

import com.apirest.Models.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User,Integer> {
    User findByUsername(String username);
}
