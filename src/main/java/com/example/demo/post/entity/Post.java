package com.example.demo.post.entity;

import com.example.demo.post.requestdto.PostRequestDTO;
import com.example.demo.user.entity.User;
import com.example.demo.utils.BaseTime;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="posts")
@NoArgsConstructor
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String title;

    private Long postLikeCount;

    //어노테이션은 현재 엔터티(User 클래스)가 다른 엔터티(User 클래스의 인스턴스)와 다대일(N:1) 관계를 맺고 있다는 것을 나타냅니다. 즉, 여러 개의 게시글이 하나의 사용자(User)에 속할 수 있습니다.
    //FetchType.LAZY는 이 관계를 지연 로딩으로 설정하며, 게시글이 실제로 사용자 정보가 필요한 시점에 데이터베이스에서 로딩됩니다.
    @ManyToOne(fetch = FetchType.LAZY)
    //어노테이션은 외래 키를 지정합니다. 현재 엔터티(User 클래스)의 테이블에는 user_id라는 외래 키가 있어야 합니다. 이 외래 키는 현재 엔터티와 연결된 다른 엔터티(User 클래스)의 기본 키를 가리킵니다
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    private Post(String content, String title, User user){
        this.content = content;
        this.title=title;
        this.user=user;
        this.postLikeCount=0L;
    }

    public void postUpdate(PostRequestDTO postRequestDTO){
        this.content = postRequestDTO.getContent();
        this.title = postRequestDTO.getTitle();
    }

    public void updatePostLikeCnt(Boolean likeState){
        if(likeState==false){
            postLikeCount++;
        } else if (likeState==true) {
            postLikeCount--;
        }
    }
}
