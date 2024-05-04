package com.ajes.repository;

import com.ajes.model.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDetailsRepository extends JpaRepository<BankDetails,Integer> {
}
