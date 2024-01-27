package com.example.lubby.users;

import com.example.lubby.users.dto.CreateUserDTO;
import com.example.lubby.users.services.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private IUsersService usersService;

    @GetMapping
    public List<UserEntity> getUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return usersService.getUserById(id).orElse(null);
    }

    @PostMapping
    public UserEntity createUser(@RequestBody CreateUserDTO createUserDTO) {
        return usersService.createUser(createUserDTO);
    }

    @PutMapping("/{id}")
    public UserEntity updateUserById(@PathVariable Long id, CreateUserDTO createUserDTO) {
        return usersService.updateUserById(id, createUserDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        usersService.deleteUserById(id);
    }
}
