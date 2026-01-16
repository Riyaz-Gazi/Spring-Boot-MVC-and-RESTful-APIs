package com.codingshuttle.springwebtutorial.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
