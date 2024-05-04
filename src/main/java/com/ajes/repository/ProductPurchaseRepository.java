package com.ajes.repository;

import com.ajes.model.ProductPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPurchaseRepository extends JpaRepository<ProductPurchase,Long> {
}
