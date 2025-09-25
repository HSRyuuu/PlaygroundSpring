package com.hsryuuu.base.jpa.repository;

import com.hsryuuu.base.jpa.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {
}
