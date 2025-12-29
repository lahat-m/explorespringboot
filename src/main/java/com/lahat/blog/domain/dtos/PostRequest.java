package com.lahat.blog.domain.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for
 */
public record PostRequest(
        @NotNull @Size(max = 250) String title,
        @NotNull @Size(max = 250) String slug,
        @NotNull String content,
        @NotNull UserRequest createdBy,
        @NotNull Long userId,
        Instant updatedAt){
}