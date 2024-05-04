package com.ajes.repository;

import com.ajes.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory , Long> {
}
