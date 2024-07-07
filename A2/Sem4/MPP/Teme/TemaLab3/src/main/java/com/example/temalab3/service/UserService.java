package com.example.temalab3.service;

import com.example.temalab3.domain.User;
import com.example.temalab3.exceptions.ServiceException;
import com.example.temalab3.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository repository){
        this.userRepository = repository;
    }

    public User login(String username, String password)
    {
        User user = userRepository.login(username, password);
        if(user == null)
            throw new ServiceException("Autentificare esuata!");
        return user;
    }
}
