package com.lahat.blog.domain;

import com.lahat.blog.domain.dtos.CommentRequest;
import com.lahat.blog.domain.dtos.CommentResponse;

import java.time.Instant;

class CommentMapper {

    private CommentMapper() {}

    static Comment toEntity(CommentRequest dto){
        Comment entity = new Comment();
        entity.setName(dto.name());
        entity.setContent(dto.content());
        entity.setEmail(dto.email());
        entity.setCreatedAt(Instant.now());
        return entity;
    }

    static CommentResponse toResponse(Comment entity) {
        return new CommentResponse(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                PostMapper.toResponse(entity.getPost())
        );
    }
}
