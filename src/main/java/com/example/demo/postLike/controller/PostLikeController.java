package com.example.demo.postLike.controller;

import com.example.demo.postLike.responseDTO.LikeResponseDTO;
import com.example.demo.postLike.service.PostLikeService;
import com.example.demo.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/posts")
@RestController
@AllArgsConstructor
public class PostLikeController {
    private final PostLikeService postLikeService;
    @PatchMapping("/{postId}/like")
    public LikeResponseDTO likeSwitch(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                      @PathVariable Long postId){
        return postLikeService.likeSwitch(userDetails.getUser(),postId);
    }

}
