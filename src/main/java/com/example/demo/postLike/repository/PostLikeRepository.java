package com.example.demo.postLike.repository;

import com.example.demo.postLike.entity.PostLike;
import com.example.demo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PostLikeRepository extends JpaRepository<PostLike,Long> {

    Optional<PostLike> findByPostAndUser(com.example.demo.post.entity.Post post, User user);
}
