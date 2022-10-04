package com.fullcycle.admin.catalogo.application.castmember.retrieve.list;

import com.fullcycle.admin.catalogo.application.UseCase;
import com.fullcycle.admin.catalogo.domain.pagination.Pagination;
import com.fullcycle.admin.catalogo.domain.pagination.SearchQuery;

public sealed abstract class ListCastMemberUseCase
    extends UseCase<SearchQuery, Pagination<CastMemberListOutput>>
    permits DefaultListCastMemberUseCase {
}
