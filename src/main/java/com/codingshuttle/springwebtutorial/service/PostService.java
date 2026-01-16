package com.codingshuttle.springwebtutorial.service;

import com.codingshuttle.springwebtutorial.dto.PostResponseDTO;
import com.codingshuttle.springwebtutorial.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@Slf4j
public class PostService {

    private final RestTemplate restTemplate;

    @Value("${external.post.api}")
    private String postApiUrl;

    public PostService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PostResponseDTO getPostById(Integer postId) {
        String url = postApiUrl + "/" + postId;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<PostResponseDTO> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            entity,
                            PostResponseDTO.class
                    );

            return response.getBody();

        } catch (HttpClientErrorException.NotFound e) {
            log.warn("Post not found in external API for id {}", postId);
            throw new ResourceNotFoundException("Post not found for id " + postId);

        } catch (HttpClientErrorException e) {
            log.error("4xx error: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Client error while calling external API");

        } catch (HttpServerErrorException e) {
            log.error("5xx error: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Server error from external API");

        } catch (Exception e) {
            log.error("Unexpected error", e);
            throw new RuntimeException("External API call failed");
        }
    }

}
