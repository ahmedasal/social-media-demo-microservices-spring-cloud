package com.social.feed.forms;

import com.social.data.model.Post;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@ToString
public class PostForm {
    private String username;
    private Date Date;
    private String content;

    public static List<PostForm> PostFormConverter(List<Post> posts){
        List<PostForm> postForms = new ArrayList<>();
        for(Post post : posts){
            PostForm postForm = new PostForm();
            postForm.setUsername(post.getUser().getUsername());
            postForm.setDate(post.getPostDate());
            postForm.setContent(post.getPost());
            postForms.add(postForm);
        }
        return postForms;
    }
}
