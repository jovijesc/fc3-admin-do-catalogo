package com.fullcycle.admin.catalogo.domain.castmember;

import com.fullcycle.admin.catalogo.domain.genre.Genre;
import com.fullcycle.admin.catalogo.domain.pagination.Pagination;
import com.fullcycle.admin.catalogo.domain.pagination.SearchQuery;

import java.util.Optional;

public interface CastMemberGateway {

    Genre create(CastMember aCastMember);

    void deleteById(CastMemberID anId);

    Optional<CastMember> findById(CastMemberID anId);

    CastMember update(CastMember aCastMember);

    Pagination<CastMember> findAll(SearchQuery aQuery);
}
