package com.example.demo.post.responsedto;

import com.example.demo.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetAllPostResponseDTO {
    private String username;
    private LocalDateTime createdAt;
    private String content;
    private String title;

    public GetAllPostResponseDTO(Post post){
        this.username=post.getUser().getUsername();
        this.createdAt=post.getCreatedAt();
        this.content =post.getContent();
        this.title=post.getTitle();
    }
}
