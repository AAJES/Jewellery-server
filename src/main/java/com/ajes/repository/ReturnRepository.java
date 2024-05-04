package com.ajes.repository;

import com.ajes.model.Customer;
import com.ajes.model.Return;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnRepository extends JpaRepository<Return,Long> {
}
