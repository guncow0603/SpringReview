package com.example.demo.comment.service;

import com.example.demo.comment.RequestDTO.CommentRequestDTO;
import com.example.demo.comment.ResponseDTO.CommentResponseDTO;
import com.example.demo.comment.entity.Comment;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.post.entity.Post;
import com.example.demo.post.repository.PostRepository;
import com.example.demo.post.service.PostService;
import com.example.demo.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private PostService postService;
    public CommentResponseDTO createComment(Long postId, CommentRequestDTO commentRequestDTO, User user) {
        Post post =postService.findByPostId(postId);
        Comment comment= Comment.builder()
                                .text(commentRequestDTO.getText())
                                .post(post)
                                .user(user)
                                .build();

        commentRepository.save(comment);

        return new CommentResponseDTO(comment);
    }

    public void deleteComment(Long postId, Long commentId, User user) {
        Comment comment=findByCommentId(commentId);
        checkCommentUser(comment,user);

        commentRepository.delete(comment);
    }

    @Transactional
    public CommentResponseDTO updateComment(Long postId, Long commentId, User user, CommentRequestDTO commentRequestDTO) {
        Comment comment=findByCommentId(commentId);
        checkCommentUser(comment,user);
        comment.commentUpdate(commentRequestDTO);

        return new CommentResponseDTO(comment);
    }




    public Comment findByCommentId(Long commentId) {
        //findById 메서드를 사용하여 Post 엔티티를 찾을 때는 반환 타입이 Optional<Post>이므로,
        // 메서드에서 이를 적절히 처리해야 합니다. 따라서 Optional<Post>에서 실제 Post 객체를 얻기 위해서는
        // Optional 클래스의 메서드 중 하나인 orElse(null)이나 orElseThrow() 등을 사용해야 합니다.
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("ID가 " + commentId + "인 게시물을 찾을 수 없습니다." + commentId));
    }
    public void checkCommentUser(Comment comment,User user){
        if((comment.getUser().getId())!= user.getId()){
            throw new IllegalArgumentException("댓글 작성자와 수정자 id가 다릅니다.");
        }
    }


}
