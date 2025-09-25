package com.hsryuuu.base.jpa.repository;


import com.hsryuuu.base.jpa.entity.AppUser;
import com.hsryuuu.base.jpa.entity.QAppUser;
import com.hsryuuu.base.jpa.entity.QOrder;
import com.hsryuuu.base.jpa.entity.QPayment;
import com.hsryuuu.base.jpa.entity.QUserGroup;
import com.hsryuuu.base.jpa.model.UserDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CustomUserRepository {

    private final JPAQueryFactory queryFactory;

    QAppUser user = QAppUser.appUser;
    QUserGroup group = QUserGroup.userGroup;
    QOrder order = QOrder.order;
    QPayment payment = QPayment.payment;

    public Optional<AppUser> findByName(String name) {
        AppUser appUser = queryFactory
                .selectFrom(user)
                .leftJoin(user.orders, order).fetchJoin()
                .leftJoin(user.payments, payment).fetchJoin()
                .where(user.name.eq(name))
                .fetchFirst();
        return Optional.ofNullable(appUser);
    }

    public List<AppUser> findUsers(long limit){
        return queryFactory
                .selectFrom(user)
                .leftJoin(user.orders, order).fetchJoin()
                .leftJoin(user.payments, payment).fetchJoin()
                .limit(limit)
                .fetch();
    }

    /**
     * Cartesian Product 문제 발생
     */
    public List<UserDto> searchUserBadExample(List<String> paymentMethods, List<String> orderItemNames, Pageable pageable) {

        return queryFactory
                .select(Projections.fields(
                        UserDto.class,
                        user.id,
                        user.name.as("username"),
                        group.name.as("groupName")
                ))
                .from(user)
                .leftJoin(user.orders, order)
                .leftJoin(user.payments, payment)
                .where(order.itemName.in(orderItemNames).or(payment.method.in(paymentMethods)))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
    }

    /**
     * Cartesian Product 문제 발생 방지
     */
public List<UserDto> searchUser(List<String> paymentMethods, List<String> orderItemNames, Pageable pageable) {
    List<UUID> userIds = queryFactory.select(user.id)
            .distinct()
            .from(user)
            .leftJoin(user.orders, order)
            .leftJoin(user.payments, payment)
            .where(order.itemName.in(orderItemNames).or(payment.method.in(paymentMethods)))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    if(userIds.isEmpty()) {
        return List.of();
    }

    return queryFactory
            .select(Projections.fields(
                    UserDto.class,
                    user.id,
                    user.name.as("username")
            ))
            .from(user)
            .where(user.id.in(userIds))
            .fetch();
}

}
