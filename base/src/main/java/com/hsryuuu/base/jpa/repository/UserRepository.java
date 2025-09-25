package com.hsryuuu.base.jpa.repository;

import com.hsryuuu.base.jpa.entity.AppUser;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {

    @Query("""
        SELECT u
        FROM AppUser u
        LEFT JOIN FETCH u.orders
        LEFT JOIN FETCH u.payments WHERE u.name = :name
        """
    )
    Optional<AppUser> findWithOrdersAndPaymentsByName(@Param("name") String name);
}
