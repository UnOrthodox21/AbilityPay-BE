package com.rihardsedmundscerps.abilitypay.controllers;

import com.rihardsedmundscerps.abilitypay.items.BankAccountItem;
import com.rihardsedmundscerps.abilitypay.services.BankAccountService;
import com.rihardsedmundscerps.abilitypay.services.MyUserDetailsService;
import com.rihardsedmundscerps.abilitypay.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final TransactionService transactionService;
    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService, TransactionService transactionService, MyUserDetailsService myUserDetailsService) {
        this.bankAccountService = bankAccountService;
        this.transactionService = transactionService;
        this.myUserDetailsService = myUserDetailsService;
    }


    @GetMapping("/getAllAccounts/{password}")
    public List<BankAccountItem> getAllBankAccounts(@PathVariable("password") String password) {
        if (myUserDetailsService.checkAdminPassword(password)) {
            return bankAccountService.getAllBankAccounts();
        }
        return null;
    }


    @GetMapping("/{accountNumber}")
    public Optional<BankAccountItem> getBankAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber){
        return bankAccountService.getBankAccountByAccountNumber(accountNumber);
    }

    @GetMapping("/getByUserId/{userId}")
    public List<BankAccountItem> getBankAccountByAccountNumber(@PathVariable("userId") Long userId){
        return bankAccountService.getBankAccountsByUserId(userId);
    }

    @PostMapping
    public void addNewBankAccount(@RequestBody BankAccountItem bankAccountItem) {
        bankAccountService.addNewBankAccount(bankAccountItem);
    }

    @PostMapping("/createForUser/{userId}")
    public void createNewBankAccountForUser(@PathVariable("userId") Long userId) {
        String newAccountNumber = "ABLTY" + Math.round(Math.floor(Math.random() * (99999999 - 10000000 + 1)) + 10000000);
        BankAccountItem newBankAccountItem = new BankAccountItem.Builder().number(newAccountNumber).type("Secondary").balance(0).userId(userId).build();
        bankAccountService.addNewBankAccount(newBankAccountItem);
    }

    @DeleteMapping("/{accountNumber}")
    public void deleteBankAccount(@PathVariable("accountNumber") String accountNumber) {
        bankAccountService.deleteBankAccount(accountNumber);
    }

    @PutMapping("/{accountNumber}")
    public void editBankAccount(@RequestBody BankAccountItem bankAccountItem, @PathVariable("accountNumber") String accountNumber){
        bankAccountService.editBankAccountByAccountNumber(bankAccountItem, accountNumber);
    }

    @PutMapping("/transfer")
    public boolean sendFunds(@RequestBody TransactionService.TransferFromTo transferFromTo) {
        boolean success = bankAccountService.sendFunds(transferFromTo);
        if(success){
            transactionService.addNewTransferTransaction(transferFromTo);
        }
        else{
            transactionService.addNewFailedTransaction(transferFromTo);
        }
        return success;
    }

    @PutMapping("/deposit")
    public void depositFunds(@RequestBody TransactionService.TransferFromTo transferFromTo){
        boolean success = bankAccountService.depositFunds(transferFromTo);
        if(success){
            transactionService.addNewDepositTransaction(transferFromTo);
        }
        else{
            transactionService.addNewFailedTransaction(transferFromTo);
        }
    }

    @PutMapping("/withdraw")
    public void withdrawFunds(@RequestBody TransactionService.TransferFromTo transferFromTo){
        boolean success = bankAccountService.withdrawFunds(transferFromTo);
        if(success){
            transactionService.addNewWithdrawTransaction(transferFromTo);
        }
        else{
            transactionService.addNewFailedTransaction(transferFromTo);
        }
    }


}
