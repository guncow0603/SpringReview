package com.example.demo.post.responsedto;

import com.example.demo.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponseDTO {

    private String username;
    private String title;
    private String content;
    private Long postLikeCount;
    public PostResponseDTO(Post post){
        this.username=post.getUser().getUsername();
        this.title= post.getTitle();
        this.content = post.getContent();
        this.postLikeCount=post.getPostLikeCount();
    }
}
