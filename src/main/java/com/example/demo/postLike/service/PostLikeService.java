package com.example.demo.postLike.service;

import com.example.demo.post.entity.Post;
import com.example.demo.post.service.PostService;
import com.example.demo.postLike.entity.PostLike;
import com.example.demo.postLike.repository.PostLikeRepository;
import com.example.demo.postLike.responseDTO.LikeResponseDTO;
import com.example.demo.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PostLikeService {
    private final PostService postService;
    private final PostLikeRepository postLikeRepository;
    @Transactional
    public LikeResponseDTO likeSwitch(User user, Long postId) {
        Post post =postService.findById(postId);

        // 댓글에 대한 좋아요 정보를 찾거나, 없으면 생성하여 가져옴
        PostLike postLike = postLikeRepository.findByPostAndUser(post,user)
                .orElseGet(() -> savePostLike(post,user));

        post.updatePostLikeCnt(postLike.isLikeState());
        postLike.updatePostLikeState();

        return new LikeResponseDTO(postLike);
    }
    @Transactional
    public PostLike savePostLike(Post post, User user) {
        PostLike postLike=PostLike.builder()
                .post(post)
                .user(user)
                .build();

        return postLikeRepository.save(postLike);
    }


}
