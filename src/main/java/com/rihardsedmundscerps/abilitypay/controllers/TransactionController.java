package com.rihardsedmundscerps.abilitypay.controllers;

import com.rihardsedmundscerps.abilitypay.items.TransactionItem;
import com.rihardsedmundscerps.abilitypay.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionItem> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/find/{accountNumber}")
    public List<TransactionItem> getAllTransactionsByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        return transactionService.getAllBankAccountTransactions(accountNumber);
    }

    @GetMapping(path = "{transactionId}")
    public Optional<TransactionItem> getTransaction(@PathVariable("transactionId") Long transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @PostMapping
    public void addNewTransaction(@RequestBody TransactionItem transactionItem) {
        transactionService.addNewTransaction(transactionItem);
    }

    @DeleteMapping(path = "{transactionId}")
    public void deleteTransaction(@PathVariable("transactionId") Long transactionId) {
        transactionService.deleteTransaction(transactionId);
    }

}
