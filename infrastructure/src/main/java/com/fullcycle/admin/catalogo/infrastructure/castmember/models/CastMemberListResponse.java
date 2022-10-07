package com.fullcycle.admin.catalogo.infrastructure.castmember.models;

import com.fullcycle.admin.catalogo.domain.castmember.CastMemberType;

public record CastMemberListResponse(
        String id,
        String name,
        String type,
        String createdAt
) {
}
