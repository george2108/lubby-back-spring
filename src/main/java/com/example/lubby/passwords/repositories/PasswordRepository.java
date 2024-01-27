package com.example.lubby.passwords.repositories;

import com.example.lubby.passwords.PasswordEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends CrudRepository<PasswordEntity, Long> {
}
