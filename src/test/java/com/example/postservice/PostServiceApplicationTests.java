package com.example.postservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = PostServiceTestConfiguration.class)

class PostServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
