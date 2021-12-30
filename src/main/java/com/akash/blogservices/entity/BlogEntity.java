package com.akash.blogservices.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BLOGS")
public class BlogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String blogTitle;
    private String blogBody;
    private Integer authorId;
    private Integer approvedId;
    private LocalDate createDateTime;
    private String blogStatus;
}
