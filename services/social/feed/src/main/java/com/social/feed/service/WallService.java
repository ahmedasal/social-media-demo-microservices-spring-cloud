package com.social.feed.service;



import com.social.data.model.Post;
import javax.persistence.EntityManager;

import com.social.feed.forms.PostForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class WallService {
    @Autowired
    private PostService postService;


    public List<PostForm>getWallPosts(EntityManager em, int userId, int offset, int noOfRows) {

        List<Post> posts = em.createQuery("select post from Post post join Friendship f ON (f.user1.id = :user and post.user.id = f.user2.id) or (f.user2.id = :user and post.user.id = f.user1.id) ")
                .setParameter("user", userId)
                .setFirstResult(offset)
                .setMaxResults(noOfRows)
                .getResultList();
//        PostService postService = new PostService();
//        for (Post post : posts) {
//            post.setLikedByMe(postService.likedByMe(em, userId, post.getId()));
//        }


        return PostForm.PostFormConverter(posts);
    }
}
