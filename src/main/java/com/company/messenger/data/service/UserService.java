package com.company.messenger.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.company.messenger.data.Repository;
import com.company.messenger.data.entity.User;

@Component
public class UserService {

    private final Repository repository;

    @Autowired
    public UserService(Repository repository) {
        this.repository = repository;
    }

    public boolean register(User user) {
        if (repository.findByColumn(User.class, "email", user.getEmail()) == null) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            repository.save(user);
            return true;
        }

        return false;
    }

    public List<User> findAll() {
        return repository.findAll(User.class);
    }

}
