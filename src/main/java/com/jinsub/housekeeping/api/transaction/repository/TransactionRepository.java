package com.jinsub.housekeeping.api.transaction.repository;

import com.jinsub.housekeeping.api.transaction.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
