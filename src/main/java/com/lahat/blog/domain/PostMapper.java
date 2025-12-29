package com.lahat.blog.domain;

import com.lahat.blog.domain.dtos.PostResponse;

class PostMapper {

    private PostMapper() {}

    static PostResponse toResponse(Post entity) {
        return new PostResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getSlug(),
                entity.getContent(),
                UserMapper.toResponse(entity.getCreatedBy()),
                entity.getUserId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
