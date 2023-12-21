package com.example.demo.post.service;

import com.example.demo.post.entity.Post;
import com.example.demo.post.repository.PostRepository;
import com.example.demo.post.requestdto.PostRequestDTO;
import com.example.demo.post.responsedto.GetAllPostResponseDTO;
import com.example.demo.post.responsedto.PostResponseDTO;
import com.example.demo.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO, User user) {

        Post post=Post.builder().
                content(postRequestDTO.getContent()).
                title(postRequestDTO.getTitle()).
                user(user).
                build();

        postRepository.save(post);

        return PostResponseDTO.builder()
                .username(user.getUsername())
                .title(postRequestDTO.getTitle())
                .content(postRequestDTO.getContent())
                .build();
    }

    public List<GetAllPostResponseDTO> getPosts(User user) {
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(GetAllPostResponseDTO::new)
                .toList();
    }
    @Transactional
    public PostResponseDTO updatePost(Long postId,PostRequestDTO postRequestDTO, User user) {
        Post post = findById(postId);

        if((post.getUser().getId())!= user.getId()){
            throw new IllegalArgumentException("게시글 작성자와 수정자 id가 다릅니다.");
        }

        post.updatePost(postRequestDTO);

        return PostResponseDTO.builder()
                .content(post.getContent())
                .title(post.getTitle())
                .username(post.getUser().getUsername())
                .build();
    }

    public Post findById(Long postId) {
        //findById 메서드를 사용하여 Post 엔티티를 찾을 때는 반환 타입이 Optional<Post>이므로,
        // 메서드에서 이를 적절히 처리해야 합니다. 따라서 Optional<Post>에서 실제 Post 객체를 얻기 위해서는
        // Optional 클래스의 메서드 중 하나인 orElse(null)이나 orElseThrow() 등을 사용해야 합니다.
        return postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("ID가 " + postId + "인 게시물을 찾을 수 없습니다." + postId));
    }
}
