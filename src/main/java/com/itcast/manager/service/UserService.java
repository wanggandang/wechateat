package com.itcast.manager.service;

import com.itcast.manager.entity.User;
import com.itcast.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> get() {
        List<User> userList = userRepository.findAll();

        return userList;
    }
}
