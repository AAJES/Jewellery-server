package com.ajes.repository;

import com.ajes.model.Category;
import com.ajes.model.Metal;
import com.ajes.model.Product;
import com.ajes.model.Purity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

    @Query(nativeQuery = true, value =
            "SELECT p.* " +
                    "FROM Product p " +
                    "INNER JOIN Category c ON p.category_id = c.category_id " +
                    "INNER JOIN Metal m ON p.metal_id = m.metal_id " +
                    "INNER JOIN Purity pu ON p.purity_id = pu.purity_id " +
                    "WHERE (:category IS NULL OR c.name = :category) " +
                    "AND (:metal IS NULL OR m.name = :metal) " +
                    "AND (:purity IS NULL OR pu.name = :purity) " +
                    "ORDER BY p.trDate ASC")
    List<Product> filterAndSortProducts(
            @Param("category") Category category,
            @Param("metal") Metal metal,
            @Param("purity") Purity purity
    );
}
