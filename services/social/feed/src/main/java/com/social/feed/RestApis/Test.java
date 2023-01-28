package com.social.feed.RestApis;

import com.social.data.model.Post;
import com.social.data.model.User;
import com.social.data.repository.ImageRepository;
import com.social.data.repository.UserRepository;
import com.social.feed.service.PostService;
import com.social.feed.service.WallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Test {

    @PersistenceContext
    private EntityManager em;

    private final WallService wallService;
    private final PostService postService;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    @GetMapping("/feed")
    public List<Post> getWallApi() {

        List<Post> wallPosts = this.wallService.getWallPosts(em,45, 0, 5);
        System.out.println(wallPosts);
        return wallPosts;
    }

    @GetMapping("/strings")
    @RolesAllowed("USER")
    public ResponseEntity<List<String>> getString(){
        return ResponseEntity.ok(List.of("ahmed", "mohamed","asal"));
    }
}
