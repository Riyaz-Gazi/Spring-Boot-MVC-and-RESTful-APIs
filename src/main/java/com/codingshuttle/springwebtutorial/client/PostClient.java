package com.codingshuttle.springwebtutorial.client;

import com.codingshuttle.springwebtutorial.config.PostApiProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class PostClient {

    private final RestTemplate restTemplate;
    private final PostApiProperties properties;

    public <T> T get(String path, Class<T> responseType) {

        String url = properties.getBaseUrl() + path;
        log.info("Calling external URL: {}", url);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<T> response =
                    restTemplate.exchange(
                            properties.getBaseUrl() + path,
                            HttpMethod.GET,
                            entity,
                            responseType
                    );

            return response.getBody();

        } catch (HttpClientErrorException.NotFound e) {
            log.warn("Resource not found from Post API: {}", path);
            throw e; // let service/advice decide

        } catch (HttpClientErrorException e) {
            log.error("4xx error from Post API: {}", e.getResponseBodyAsString());
            throw e;

        } catch (HttpServerErrorException e) {
            log.error("5xx error from Post API: {}", e.getResponseBodyAsString());
            throw e;

        } catch (Exception e) {
            log.error("Unexpected error calling Post API", e);
            throw new RuntimeException("External API call failed", e);
        }
    }


    @PostConstruct
    public void logBaseUrl() {
        log.info("Post API Base URL = {}", properties.getBaseUrl());
    }
}
