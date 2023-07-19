package com.company.basketservice.repository;


import com.company.basketservice.module.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {
    @Query(value = "select * from basket where deleted_at is null and id = :id", nativeQuery = true)
    Optional<Basket> findByIdAndDeletedAtIsNull(@Param(value = "id") Integer id);
}