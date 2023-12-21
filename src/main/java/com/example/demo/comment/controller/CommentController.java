package com.example.demo.comment.controller;

import com.example.demo.comment.RequestDTO.CommentRequestDTO;
import com.example.demo.comment.ResponseDTO.CommentResponseDTO;
import com.example.demo.comment.service.CommentService;
import com.example.demo.post.responsedto.PostResponseDTO;
import com.example.demo.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/comments")
@RestController
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}")
    public CommentResponseDTO createComment(@PathVariable Long postId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails,
                                            @RequestBody CommentRequestDTO commentRequestDTO){
        return commentService.createComment(postId,commentRequestDTO,userDetails.getUser());
    }
}
