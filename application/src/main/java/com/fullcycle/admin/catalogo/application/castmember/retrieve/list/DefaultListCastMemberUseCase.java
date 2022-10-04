package com.fullcycle.admin.catalogo.application.castmember.retrieve.list;

import com.fullcycle.admin.catalogo.domain.castmember.CastMemberGateway;
import com.fullcycle.admin.catalogo.domain.pagination.Pagination;
import com.fullcycle.admin.catalogo.domain.pagination.SearchQuery;

import java.util.Objects;

public final class DefaultListCastMemberUseCase extends ListCastMemberUseCase {

    private final CastMemberGateway castMemberGateway;

    public DefaultListCastMemberUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Override
    public Pagination<CastMemberListOutput> execute(final SearchQuery aQuery) {
        return this.castMemberGateway.findAll(aQuery)
                .map(CastMemberListOutput::from);
    }
}
