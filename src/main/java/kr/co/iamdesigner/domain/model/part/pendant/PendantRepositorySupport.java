package kr.co.iamdesigner.domain.model.part.pendant;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class PendantRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public PendantRepositorySupport(JPAQueryFactory queryFactory) {
        super(Pendant.class);
        this.queryFactory = queryFactory;
    }
}
