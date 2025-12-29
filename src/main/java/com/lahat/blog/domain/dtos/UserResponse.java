package com.lahat.blog.domain.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {User}
 */
public record UserResponse(
        Long id, @NotNull @Size(max = 255) String email,
        @NotNull @Size(max = 255) String password,
        @NotNull @Size(max = 255) String name,
        @NotNull @Size(max = 20) String role,
        @NotNull Instant createdAt,
        Instant updatedAt){
}