package com.example.demo.post.service;

import com.example.demo.comment.ResponseDTO.CommentResponseDTO;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.post.entity.Post;
import com.example.demo.post.repository.PostRepository;
import com.example.demo.post.requestdto.PostRequestDTO;
import com.example.demo.post.responsedto.PostCommentResponseDTO;
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
    private final CommentRepository commentRepository;
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO, User user) {

        Post post=Post.builder().
                content(postRequestDTO.getContent()).
                title(postRequestDTO.getTitle()).
                user(user).
                build();

        postRepository.save(post);

        return new PostResponseDTO(post);
    }
    @Transactional
    public List<PostResponseDTO> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(PostResponseDTO::new)
                .toList();
    }
    @Transactional
    public PostCommentResponseDTO getPost(Long postId) {
        Post post= findByPostId(postId);
        List<CommentResponseDTO>commentList=commentRepository.findAllByOrderByCreatedAtDesc()
                                                             .stream()
                                                             .map(CommentResponseDTO::new)
                                                             .toList();
        return new PostCommentResponseDTO(post,commentList);
    }


    @Transactional
    public PostResponseDTO updatePost(Long postId,PostRequestDTO postRequestDTO, User user) {
        Post post = findByPostId(postId);
        checkUser(post,user);
        post.postUpdate(postRequestDTO);

        return new PostResponseDTO(post);
    }
    @Transactional
    public void deletePost(Long postId, User user) {
        Post post = findByPostId(postId);
        checkUser(post,user);

        postRepository.delete(post);
    }



    public Post findByPostId(Long postId) {
        //findById 메서드를 사용하여 Post 엔티티를 찾을 때는 반환 타입이 Optional<Post>이므로,
        // 메서드에서 이를 적절히 처리해야 합니다. 따라서 Optional<Post>에서 실제 Post 객체를 얻기 위해서는
        // Optional 클래스의 메서드 중 하나인 orElse(null)이나 orElseThrow() 등을 사용해야 합니다.
        return postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("ID가 " + postId + "인 게시물을 찾을 수 없습니다." + postId));
    }
    public void checkUser(Post post,User user){
        if((post.getUser().getId())!= user.getId()){
            throw new IllegalArgumentException("게시글 작성자와 수정자 id가 다릅니다.");
        }
    }


}
