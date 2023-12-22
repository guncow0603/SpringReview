package com.example.demo.comment.service;

import com.example.demo.comment.RequestDTO.CommentRequestDTO;
import com.example.demo.comment.ResponseDTO.CommentResponseDTO;
import com.example.demo.comment.entity.Comment;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.post.entity.Post;
import com.example.demo.post.repository.PostRepository;
import com.example.demo.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    public CommentResponseDTO createComment(Long postId, CommentRequestDTO commentRequestDTO, User user) {
        Post post =findById(postId);
        Comment comment= Comment.builder()
                                .text(commentRequestDTO.getText())
                                .post(post)
                                .username(user.getUsername())
                                .build();

        commentRepository.save(comment);

        return new CommentResponseDTO(comment);
    }

    private Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                ()-> new NoSuchElementException("ID가 " + postId + "인 게시물을 찾을 수 없습니다." + postId));

    }
}
