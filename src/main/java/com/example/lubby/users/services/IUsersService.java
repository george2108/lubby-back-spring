package com.example.lubby.users.services;

import com.example.lubby.users.dto.CreateUserDTO;
import com.example.lubby.users.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUsersService {
    public UserEntity createUser(CreateUserDTO createUserDTO);

    public Optional<UserEntity> getUserById(Long id);

    public void deleteUserById(Long id);

    public UserEntity updateUserById(Long id, CreateUserDTO createUserDTO);

    public List<UserEntity> getAllUsers();
}
