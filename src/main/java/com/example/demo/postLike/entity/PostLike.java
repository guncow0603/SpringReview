package com.example.demo.postLike.entity;

import com.example.demo.post.entity.Post;
import com.example.demo.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "postLikes")
@Getter
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean likeState;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public PostLike(boolean likeState,Post post,User user){
        this.likeState=likeState;
        this.post=post;
        this.user=user;
    }

    public boolean updatePostLikeState(){
        return this.likeState=!likeState;
    }
}
