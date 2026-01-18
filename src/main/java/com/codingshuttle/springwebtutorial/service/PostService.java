package com.codingshuttle.springwebtutorial.service;

import com.codingshuttle.springwebtutorial.client.PostClient;
import com.codingshuttle.springwebtutorial.dto.PostResponseDTO;
import com.codingshuttle.springwebtutorial.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostClient postClient;

    public PostResponseDTO getPostById(Integer postId) {

        try {
            return postClient.get("/" + postId, PostResponseDTO.class);

        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException(
                    "Post not found for id " + postId
            );
        }
    }

}
