package com.lahat.blog.domain.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for comment
 */
public record CommentRequest(
        @NotNull @Size(max = 250) String name,
        @NotNull @Size(max = 250) String email,
        @NotNull String content
){
}