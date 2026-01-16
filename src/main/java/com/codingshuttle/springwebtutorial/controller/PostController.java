package com.codingshuttle.springwebtutorial.controller;

import com.codingshuttle.springwebtutorial.dto.PostResponseDTO;
import com.codingshuttle.springwebtutorial.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/{postId}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable(value = "postId") Integer postId) {
        PostResponseDTO postResponseDTO = postService.getPostById(postId);
        return new ResponseEntity<>(postResponseDTO, HttpStatus.OK);
    }
}
