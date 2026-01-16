package com.codingshuttle.springwebtutorial.service;

import com.codingshuttle.springwebtutorial.dto.PostResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void testForGetPostById(){
        PostResponseDTO postResponseDTO = postService.getPostById(1);
        System.out.println(postResponseDTO);
    }

}