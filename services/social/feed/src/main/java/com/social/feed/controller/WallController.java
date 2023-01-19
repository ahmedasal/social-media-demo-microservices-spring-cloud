package com.social.feed.controller;


import com.social.data.repository.ImageRepository;
import com.social.data.repository.UserRepository;
import com.social.model.Image;
import com.social.model.Post;
import com.social.model.User;
import com.social.feed.service.PostService;
import com.social.feed.service.WallService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Controller

public class WallController {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private WallService wallService;
    @Autowired
    private PostService postService;
   @Autowired
    private ImageRepository imageRepository;
    private Object principal = null;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/wall")
    public String getWall(Model model) {
//        principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        user = ((ModifiedUserDetails) principal).getUser();

        User user = userRepository.findById(46).get();
        System.out.println(user);
        System.out.println(user.getId());

        List<Post> wallPosts = this.wallService.getWallPosts(em, user.getId(), 0, 5);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("wallPosts", wallPosts);
        return "wallPage";
    }

    @PostMapping("/post")
    public ModelAndView addPost(@RequestParam("message") String postText, @RequestParam("photos") MultipartFile[] files, Model model) throws Exception {
        User user = userRepository.findById(46).get();
        Post post = new Post();
        post.setUser(user);
        post.setPostDate(new Date());
        post.setPost(postText);
        postService.writePost(post);
        System.out.println(files.length);
        ////////////// for upload images ////////////////////
            for (int i = 0; i < files.length; i++) {
                Image img = new Image();
                InputStream inputStream = files[i].getInputStream();
                byte[] inputStreamByte = inputStream.readAllBytes();
                if (inputStreamByte.length > 0) {
                    Blob imgBlob = null;
                    try {
                        imgBlob = new SerialBlob(inputStream.readAllBytes());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    img.setPost(post);
                    img.setImg(imgBlob);
                    imageRepository.save(img);
                }
            }
        //////////////######################////////////////////
        return new ModelAndView("redirect:/wall");
    }




    @GetMapping("/image")
    public void getImageAsByteArray(@RequestParam("id") String id, HttpServletResponse resp) throws IOException, SQLException {
        Image image = imageRepository.findImageById(Integer.parseInt(id));
        resp.setContentType("image/jpeg");
        resp.getOutputStream().write(image.getImg().getBinaryStream().readAllBytes());
        resp.getOutputStream().flush();
    }

}