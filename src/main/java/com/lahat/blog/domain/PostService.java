package com.lahat.blog.domain;

import com.lahat.blog.domain.dtos.PostRequest;
import com.lahat.blog.domain.dtos.PostResponse;
import com.lahat.blog.web.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public PostResponse createPost(PostRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.userId()));

        Post post = new Post();
        post.setTitle(request.title());
        post.setSlug(request.slug());
        post.setContent(request.content());
        post.setCreatedBy(user);
        post.setUserId(request.userId());
        post.setCreatedAt(Instant.now());
        post.setUpdatedAt(request.updatedAt());

        Post saved = postRepository.save(post);
        return PostMapper.toResponse(saved);
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        return PostMapper.toResponse(post);
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PostResponse updatePost(Long id, PostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.userId()));

        post.setTitle(request.title());
        post.setSlug(request.slug());
        post.setContent(request.content());
        post.setCreatedBy(user);
        post.setUserId(request.userId());
        post.setUpdatedAt(Instant.now());

        Post updated = postRepository.save(post);
        return PostMapper.toResponse(updated);
    }

    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
    }
}
