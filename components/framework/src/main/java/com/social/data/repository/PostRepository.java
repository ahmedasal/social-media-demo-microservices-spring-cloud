package com.social.data.repository;



import com.social.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostById(int posId);

}
