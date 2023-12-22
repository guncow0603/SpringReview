package com.example.demo.post.responsedto;

import com.example.demo.comment.ResponseDTO.CommentResponseDTO;
import com.example.demo.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostCommentResponseDTO {

    private String username;
    private String title;
    private String content;
    private Long postLikeCount;
    private LocalDateTime createdAt;
    private List<CommentResponseDTO> commentList;
    public PostCommentResponseDTO(Post post,List<CommentResponseDTO> commentList){
        this.username=post.getUser().getUsername();
        this.title= post.getTitle();
        this.content = post.getContent();
        this.postLikeCount=post.getPostLikeCount();
        this.createdAt=post.getCreatedAt();
        this.commentList=commentList;
    }
}
