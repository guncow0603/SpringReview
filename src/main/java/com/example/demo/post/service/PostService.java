package com.example.demo.post.service;

import com.example.demo.post.entity.Post;
import com.example.demo.post.repository.PostRepository;
import com.example.demo.post.requestdto.PostRequestDTO;
import com.example.demo.post.responsedto.GetAllPostResponseDTO;
import com.example.demo.post.responsedto.PostResponseDTO;
import com.example.demo.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO, User user) {

        Post post=Post.builder().
                comment(postRequestDTO.getComment()).
                title(postRequestDTO.getTitle()).
                user(user).
                build();

        postRepository.save(post);

        return PostResponseDTO.builder()
                .username(user.getUsername())
                .title(postRequestDTO.getTitle())
                .comment(postRequestDTO.getComment())
                .build();
    }

    public List<GetAllPostResponseDTO> getPosts(User user) {
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(GetAllPostResponseDTO::new)
                .toList();
    }
}
