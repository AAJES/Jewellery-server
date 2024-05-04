package com.ajes.repository;

import com.ajes.model.Metal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetalRepository extends JpaRepository<Metal,Integer> {
}
