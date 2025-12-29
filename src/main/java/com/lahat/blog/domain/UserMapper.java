package com.lahat.blog.domain;

import com.lahat.blog.domain.dtos.UserRequest;
import com.lahat.blog.domain.dtos.UserResponse;

import java.time.Instant;

class UserMapper {

    private UserMapper(){}

    static User toEntity(UserRequest dto){
        User entity = new User();
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());
        entity.setName(dto.name());
        entity.setRole(dto.role());
        entity.setCreatedAt(Instant.now());
        return entity;
    }

    static UserResponse toResponse(User entity){
        return new UserResponse(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getName(),
                entity.getRole(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
