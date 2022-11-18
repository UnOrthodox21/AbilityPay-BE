package com.rihardsedmundscerps.abilitypay.repositories;

import com.rihardsedmundscerps.abilitypay.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findTransactionById(Long id);

}
