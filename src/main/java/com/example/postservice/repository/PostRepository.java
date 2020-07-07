package com.example.postservice.repository;


import com.example.postservice.model.Post;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface PostRepository extends ReactiveCrudRepository<Post, Long> {
}