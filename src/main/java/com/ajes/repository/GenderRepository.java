package com.ajes.repository;

import com.ajes.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender,Integer> {
}
