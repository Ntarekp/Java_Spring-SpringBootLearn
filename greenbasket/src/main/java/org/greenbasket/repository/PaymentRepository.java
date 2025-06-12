package org.greenbasket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.greenbasket.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Custom query methods (if needed) can be defined here.
}
