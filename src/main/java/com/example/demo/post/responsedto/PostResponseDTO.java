package com.example.demo.post.responsedto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponseDTO {

    private String username;
    private String title;
    private String comment;
    @Builder
    private PostResponseDTO(String username,String title, String comment){
        this.username=username;
        this.title=title;
        this.comment = comment;
    }
}