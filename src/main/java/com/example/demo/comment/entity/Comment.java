package com.example.demo.comment.entity;

import com.example.demo.comment.RequestDTO.CommentRequestDTO;
import com.example.demo.post.entity.Post;
import com.example.demo.user.entity.User;
import com.example.demo.utils.BaseTime;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Builder
    public Comment(String text, Post post, User user){
        this.text=text;
        this.post=post;
        this.user=user;
    }

    public void commentUpdate(CommentRequestDTO commentRequestDTO){
        this.text=commentRequestDTO.getText();
    }
}
