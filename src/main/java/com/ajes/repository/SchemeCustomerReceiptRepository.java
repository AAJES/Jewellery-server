package com.ajes.repository;

import com.ajes.model.SchemeCustomerReceipt;
import com.ajes.model.SchemeReceipts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchemeCustomerReceiptRepository extends JpaRepository<SchemeCustomerReceipt, Integer> {

    List<SchemeCustomerReceipt> findByCustomerLuckyNo(Integer customerLuckyNo) ;

}
