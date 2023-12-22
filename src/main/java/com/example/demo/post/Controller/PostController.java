package com.example.demo.post.Controller;

import com.example.demo.post.requestdto.PostRequestDTO;
import com.example.demo.post.responsedto.PostCommentResponseDTO;
import com.example.demo.post.responsedto.PostResponseDTO;
import com.example.demo.post.service.PostService;
import com.example.demo.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    @PostMapping
    public PostResponseDTO createPost(@RequestBody PostRequestDTO postRequestDTO,
                                      //어노테이션은 Spring Security에서 제공되며, 현재 사용자의 Principal(주체)을 주입받기 위해 사용됩니다.
                                      // UserDetailsImpl은 Spring Security에서 사용되는 UserDetails 인터페이스를 구현한 클래스.
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.createPost(postRequestDTO,userDetails.getUser());
    }
    @GetMapping
    public List<PostResponseDTO> getPosts(){
       return postService.getPosts();
    }

    @GetMapping("/{postId}")
    public PostCommentResponseDTO getPost(@PathVariable Long postId){
        return postService.getPost(postId);
    }

    @PutMapping("/{postId}")
    public PostResponseDTO updatePost(@PathVariable Long postId,
                                      @RequestBody PostRequestDTO postRequestDTO,
                                      @AuthenticationPrincipal UserDetailsImpl userDtails){
       return postService.updatePost(postId,postRequestDTO,userDtails.getUser());
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId,
                             @AuthenticationPrincipal UserDetailsImpl userDtails){
        postService.deletePost(postId,userDtails.getUser());
        return "삭제 되었습니다";
    }



}
