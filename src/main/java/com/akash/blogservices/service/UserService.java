package com.akash.blogservices.service;

import com.akash.blogservices.entity.UserEntity;
import com.akash.blogservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean checkUserExist(String userName)
    {
        UserEntity user = userRepository.findByUsername(userName);
        if(user!=null)
        {
            return true;
        }
        else
            return false;
    }
}
