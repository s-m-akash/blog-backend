package com.akash.blogservices.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDomain {
    private String userName;
    private String password;
    private String role;
    private String status;
    private String email;
}
