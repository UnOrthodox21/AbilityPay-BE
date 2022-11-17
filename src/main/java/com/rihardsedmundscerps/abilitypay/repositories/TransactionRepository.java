package com.rihardsedmundscerps.abilitypay.repositories;

import com.rihardsedmundscerps.abilitypay.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {}
