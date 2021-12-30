package com.akash.blogservices.repository;

import com.akash.blogservices.entity.BlogEntity;
import com.akash.blogservices.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {

}
