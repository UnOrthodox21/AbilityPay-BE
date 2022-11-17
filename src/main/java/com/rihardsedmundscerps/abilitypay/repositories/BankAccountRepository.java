package com.rihardsedmundscerps.abilitypay.repositories;

import com.rihardsedmundscerps.abilitypay.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {}
