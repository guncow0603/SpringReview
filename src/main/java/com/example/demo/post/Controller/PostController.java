package com.example.demo.post.Controller;

import com.example.demo.post.requestdto.PostRequestDTO;
import com.example.demo.post.responsedto.PostResponseDTO;
import com.example.demo.post.service.PostService;
import com.example.demo.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    @PostMapping("/posts")
    public PostResponseDTO createPost(@RequestBody PostRequestDTO postRequestDTO,
                                      //어노테이션은 Spring Security에서 제공되며, 현재 사용자의 Principal(주체)을 주입받기 위해 사용됩니다.
                                      // UserDetailsImpl은 Spring Security에서 사용되는 UserDetails 인터페이스를 구현한 클래스.
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.createPost(postRequestDTO,userDetails.getUser());
    }

}
