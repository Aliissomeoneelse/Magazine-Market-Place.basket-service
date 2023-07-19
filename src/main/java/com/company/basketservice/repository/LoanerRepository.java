package com.company.basketservice.repository;


import com.company.basketservice.module.Loaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanerRepository extends JpaRepository<Loaner,Integer> {
    @Query(value = "select * from loaner where deleted_at is null and id = :id", nativeQuery = true)
    Optional<Loaner> findByIdAndDeletedAtIsNull(@Param(value = "id") Integer id);
}