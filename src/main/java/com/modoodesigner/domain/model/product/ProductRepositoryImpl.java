package com.modoodesigner.domain.model.product;

import com.modoodesigner.domain.application.commands.ProductSearchCommand;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import static com.modoodesigner.domain.model.attachment.QAttachment.attachment;
import static com.modoodesigner.domain.model.product.QProduct.product;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Product getProductByIdWithImages(Long id) {
        return queryFactory.selectFrom(product)
                .join(product.images,attachment).fetchJoin()
                .fetchFirst();
    }

    @Override
    public Page<Product> findAllWithFirstImage(ProductSearchCommand command) {


        QueryResults<Product> results = queryFactory
                .selectFrom(product)
                .join(product.images,attachment).fetchJoin()
                .where(nameContains(command.getSearch()),attachment.position.eq(0))
                .offset(command.getPageable().getOffset())
                .limit(command.getPageable().getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), command.getPageable(), results.getTotal());
    }

    private BooleanExpression nameContains(String name) {
        return isEmpty(name) ? null : product.name.contains(name);
    }

    private BooleanExpression nameEq(String name) {
        return isEmpty(name) ? null : product.name.eq(name);
    }
}
