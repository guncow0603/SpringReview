package com.example.demo.comment.repository;

import com.example.demo.comment.entity.Comment;
import com.example.demo.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByOrderByCreatedAtDesc();
}
