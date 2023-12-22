package com.example.demo.comment.ResponseDTO;

import com.example.demo.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDTO {
    private String text;
    private Long postId;
    private Long commentId;
    private String username;
    private LocalDateTime createdAt;

    public CommentResponseDTO(Comment comment){
        this.text=comment.getText();
        this.postId=comment.getPost().getId();
        this.commentId=comment.getId();
        this.username=comment.getUsername();
        this.createdAt=comment.getCreatedAt();
    }
}
