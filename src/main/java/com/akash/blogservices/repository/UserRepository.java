package com.akash.blogservices.repository;

import com.akash.blogservices.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(String userName);
}
