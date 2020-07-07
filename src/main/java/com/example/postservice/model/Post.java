package com.example.postservice.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table("post")
@AllArgsConstructor
@Setter
public class Post{
    
    @Id
    private Long id;
    private String title;
    private String content;
}