package com.lahat.blog.domain;

import com.lahat.blog.domain.dtos.CommentRequest;
import com.lahat.blog.domain.dtos.CommentResponse;
import com.lahat.blog.web.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(
            CommentRepository commentRepository,
            PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentResponse createComment(Long postId, CommentRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        
        Comment comment = CommentMapper.toEntity(request);
        comment.setPost(post);
        return CommentMapper.toResponse(commentRepository.save(comment));
    }

    public CommentResponse getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + id));
        return CommentMapper.toResponse(comment);
    }

    public List<CommentResponse> getAllComments() {
        return commentRepository.findAll().stream()
                .map(CommentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CommentResponse updateComment(Long id, CommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + id));

        comment.setName(request.name());
        comment.setEmail(request.email());
        comment.setContent(request.content());
        comment.setUpdatedAt(Instant.now());

        Comment updated = commentRepository.save(comment);
        return CommentMapper.toResponse(updated);
    }

    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comment not found with id: " + id);
        }
        commentRepository.deleteById(id);
    }
}
