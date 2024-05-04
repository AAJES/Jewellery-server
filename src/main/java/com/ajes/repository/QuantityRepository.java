package com.ajes.repository;

import com.ajes.model.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface QuantityRepository extends JpaRepository<Quantity,Integer> {

    @Query("SELECT MAX(q.quantityId) FROM Quantity q")
    Integer findMaxQuantityId();

    boolean existsByQuantityId(Integer quantityId);
}
