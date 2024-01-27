package com.example.lubby.users.services;

import com.example.lubby.users.dto.CreateUserDTO;
import com.example.lubby.users.UserEntity;
import com.example.lubby.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService implements IUsersService {
    @Autowired
    private UserRepository usersRepository;

    @Override
    public UserEntity createUser(CreateUserDTO createUserDTO) {
        return usersRepository.save(
                UserEntity.builder()
                        .firstName(createUserDTO.getFirstName())
                        .lastName(createUserDTO.getLastName())
                        .email(createUserDTO.getEmail())
                        .password(createUserDTO.getPassword())
                        .build()
        );
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        UserEntity user = usersRepository.findById(id).orElse(null);
        return Optional.ofNullable(user);
    }

    @Override
    public void deleteUserById(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public UserEntity updateUserById(Long id, CreateUserDTO createUserDTO) {
        return usersRepository.save(
                UserEntity.builder()
                        .id(id)
                        .firstName(createUserDTO.getFirstName())
                        .lastName(createUserDTO.getLastName())
                        .email(createUserDTO.getEmail())
                        .password(createUserDTO.getPassword())
                        .build()
        );
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return (List<UserEntity>) usersRepository.findAll();
    }
}
