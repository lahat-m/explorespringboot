package com.lahat.blog.web;

import com.lahat.blog.domain.CommentService;
import com.lahat.blog.domain.PostService;
import com.lahat.blog.domain.UserService;
import com.lahat.blog.domain.dtos.CommentResponse;
import com.lahat.blog.domain.dtos.PostResponse;
import com.lahat.blog.domain.dtos.UserResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ViewController {
    
    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    public ViewController(PostService postService, UserService userService, CommentService commentService) {
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<PostResponse> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        List<PostResponse> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/{slug}")
    public String postDetail(@PathVariable String slug, Model model) {
        // For now, we'll need to fetch by ID or add a findBySlug method
        // Assuming slug format or we fetch all and filter
        List<PostResponse> allPosts = postService.getAllPosts();
        PostResponse post = allPosts.stream()
                .filter(p -> p.slug().equals(slug))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with slug: " + slug));
        
        List<CommentResponse> comments = commentService.getAllComments();
        
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "post-detail";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserResponse> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
