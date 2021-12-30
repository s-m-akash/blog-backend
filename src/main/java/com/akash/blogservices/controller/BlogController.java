package com.akash.blogservices.controller;

import com.akash.blogservices.domain.AuthRequest;
import com.akash.blogservices.domain.UserDomain;
import com.akash.blogservices.entity.BlogEntity;
import com.akash.blogservices.entity.UserEntity;
import com.akash.blogservices.repository.BlogRepository;
import com.akash.blogservices.repository.UserRepository;
import com.akash.blogservices.service.UserService;
import com.akash.blogservices.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/")
    public String welcome() {
        return "Welcome akash";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUserName(),
                            authRequest.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new Exception("Invalid username or password!");
        }


       // return jwtUtil.generateToken(authRequest.getUserName());
        return jwtUtil.generateToken(userRepository.findByUsername(authRequest.getUserName()));
    }

    @PostMapping("/createUser")
    public ResponseEntity addUser(@RequestBody UserEntity user )
    {
        AddResponse ad = new AddResponse();
        if(!userService.checkUserExist(user.getUsername())) {

            System.out.println("user "+user.getUsername()+" doen't exist. creating one.");
            userRepository.save(user);

            //Response header
            HttpHeaders headers = new HttpHeaders();
            headers.add("unique", user.getUsername());

            //Response body
            ad.setMsg("Success! User is added.");
            return new ResponseEntity<AddResponse>(ad, headers, HttpStatus.CREATED);
        }
        else
        {
            System.out.println("User "+user.getUsername()+" exist, so skipping creation.");
            ad.setMsg("user already exist!");
            return new ResponseEntity<AddResponse>(ad ,  HttpStatus.ACCEPTED);

        }
    }

    @PostMapping("/createBlog")
    public ResponseEntity createBlog(@RequestBody BlogEntity blog )
    {
        AddResponse ad = new AddResponse();

            blogRepository.save(blog);
            //Response header
            HttpHeaders headers = new HttpHeaders();
            headers.add("unique", blog.getId()+"");

            //Response body
            ad.setMsg("Success! Blog published");
            return new ResponseEntity<AddResponse>(ad, headers, HttpStatus.CREATED);

    }

    @GetMapping("/getUserDetailsByUserName")
    public UserEntity getUserDetails(@RequestParam(value="userName")String userName) {
        try {
            UserEntity user = userRepository.findByUsername(userName);
            return user;
        }
        catch (Exception e)
        {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllBlog")
    public List<BlogEntity> getAllBlog() {
        try {
            List<BlogEntity> blogList = blogRepository.findAll();
            return blogList;
        }
        catch (Exception e)
        {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteBlog")
    public ResponseEntity deleteBookById(@RequestParam(value="id")Integer id)
    {
        BlogEntity blog = blogRepository.findById(id).get();
        blogRepository.delete(blog);
        return new ResponseEntity<>("Blog is deleted", HttpStatus.CREATED);
    }
}
