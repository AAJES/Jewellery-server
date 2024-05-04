package com.ajes.repository;

import com.ajes.model.Scheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchemeRepository extends JpaRepository<Scheme,Integer> {
    List<Scheme> findBySchemeName(String schemeName);
}
