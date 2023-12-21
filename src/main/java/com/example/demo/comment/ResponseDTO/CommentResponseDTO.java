package com.example.demo.comment.ResponseDTO;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponseDTO {
    private String text;
    private Long postId;
    private Long commentId;
    private String username;

    @Builder
    public CommentResponseDTO(String text, Long postId, Long commentId, String username){
        this.text=text;
        this.postId=postId;
        this.commentId=commentId;
        this.username=username;
    }
}
