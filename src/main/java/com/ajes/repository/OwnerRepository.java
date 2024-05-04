package com.ajes.repository;

import com.ajes.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,Integer> {


    public Owner findByPhone(String phone);
}
