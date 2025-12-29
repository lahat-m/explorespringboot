package com.lahat.blog.domain.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

/**
 * DTO for comment
 */
public record CommentResponse(
        Long id, @NotNull @Size(max = 250) String name,
        @NotNull @Size(max = 250) String email,
        @NotNull String content,
        @NotNull Instant createdAt,
        Instant updatedAt,
        PostResponse post){
}