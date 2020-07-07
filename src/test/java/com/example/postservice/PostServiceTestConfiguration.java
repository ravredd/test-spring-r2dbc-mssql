package com.example.postservice;


import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@TestConfiguration
public class PostServiceTestConfiguration {

}