package com.example.demo.postLike.responseDTO;

import com.example.demo.postLike.entity.PostLike;
import lombok.Getter;

@Getter
public class LikeResponseDTO {
    private boolean likeState;
    private Long postLikeCnt;

    public LikeResponseDTO(PostLike postLike){
        this.likeState= postLike.isLikeState();
        this.postLikeCnt=postLike.getPost().getPostLikeCount();
    }
}
