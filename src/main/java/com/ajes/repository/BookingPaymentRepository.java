package com.ajes.repository;

import com.ajes.model.BookingPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface BookingPaymentRepository extends JpaRepository<BookingPayment,Integer> {
}
