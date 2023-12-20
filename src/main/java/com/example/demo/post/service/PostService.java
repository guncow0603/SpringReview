package com.example.demo.post.service;

import com.example.demo.post.entity.Post;
import com.example.demo.post.repository.PostRepository;
import com.example.demo.post.requestdto.PostRequestDTO;
import com.example.demo.post.responsedto.PostResponseDTO;
import com.example.demo.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO, User user) {

        Post post=Post.builder().
                post(postRequestDTO.getPost()).
                title(postRequestDTO.getTitle()).
                user(user).
                build();

        postRepository.save(post);

        return PostResponseDTO.builder()
                .username(user.getUsername())
                .title(postRequestDTO.getTitle())
                .post(postRequestDTO.getPost())
                .build();
    }
}
