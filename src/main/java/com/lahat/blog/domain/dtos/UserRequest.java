package com.lahat.blog.domain.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for User
 */
public record UserRequest(
        @NotNull @Size(max = 255) String email,
        @Size(max = 255) String password,
        @NotNull @Size(max = 255) String name,
        @NotNull @Size(max = 20) String role
){
}