package com.akash.blogservices;

import com.akash.blogservices.entity.UserEntity;
import com.akash.blogservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class BlogServicesApplication {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initUser() {
        List<UserEntity> users = Stream.of(new UserEntity(1, "akash", "password","admin",null, "smakash.bubt@gmail.com")
        ).collect(Collectors.toList());
        userRepository.saveAll(users);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*")
                        .allowedHeaders("*")
                        .allowedOriginPatterns("*")
                        .allowedMethods("*")
                        .allowCredentials(true);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogServicesApplication.class, args);
    }

}
