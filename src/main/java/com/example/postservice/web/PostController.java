package com.example.postservice.web;

import com.example.postservice.model.Post;
import com.example.postservice.repository.PostRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/")
public class PostController {
    
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Post> createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @GetMapping("/posts")
    public Flux<Post> getPosts() {
        return postRepository.findAll();
    }   
    
    @PostMapping("/posts/deleteAll")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> deleteAll() {
        return postRepository.deleteAll();
    }
}