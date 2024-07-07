package com.example.temalab3.repository;

import com.example.temalab3.domain.User;

public interface UserRepository extends Repository<Integer, User>{
    public User login(String username, String password);
}
