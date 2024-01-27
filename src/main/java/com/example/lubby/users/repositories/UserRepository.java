package com.example.lubby.users.repositories;

import com.example.lubby.users.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    public Optional<UserEntity> findByEmail(String email);
}
