package com.revolut.persons.repository;

import com.revolut.persons.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

}
