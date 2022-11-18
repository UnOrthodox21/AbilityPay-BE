package com.rihardsedmundscerps.abilitypay.services;

import com.rihardsedmundscerps.abilitypay.items.TransactionItem;
import com.rihardsedmundscerps.abilitypay.mappers.TransactionMapper;
import com.rihardsedmundscerps.abilitypay.models.Transaction;
import com.rihardsedmundscerps.abilitypay.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    public List<TransactionItem> getAllTransactions() {
        List<Transaction> allTransactions = transactionRepository.findAll();

        return allTransactions.stream()
                .map(transactionMapper::toTransactionItem)
                .collect(Collectors.toList());
    }

    public List<TransactionItem> getAllBankAccountTransactions(String accountNumber) {
        List<Transaction> allTransactions = transactionRepository.findAll();
        List<Transaction> bankAccountTransactions = new ArrayList<>();
        String nameForLookup = accountNumber;
        for (int i = 0; i < allTransactions.size(); i++) {
            if (allTransactions.get(i).getUserTo().equals(nameForLookup) || allTransactions.get(i).getUserFrom().equals(nameForLookup)) {
                bankAccountTransactions.add(allTransactions.get(i));
            }
        }

        return bankAccountTransactions.stream()
                .map(transactionMapper::toTransactionItem)
                .collect(Collectors.toList());
    }

    public Optional<TransactionItem> getTransactionById(long transactionId) {
        Optional<Transaction> foundTransaction = transactionRepository.findById(transactionId);
        TransactionItem foundTransactionItem = transactionMapper.toTransactionItem(foundTransaction.get());
        return Optional.of(foundTransactionItem);

    }

    public void addNewTransaction(TransactionItem transactionItem) {
        Transaction transactionToSave = transactionMapper.toTransaction(transactionItem);
        transactionRepository.save(transactionToSave);
    }

    public void addNewTransferTransaction(TransferFromTo transferFromTo) {
        Transaction newTransaction = new Transaction();
        if (transferFromTo.getDescription() != null) {
            newTransaction.setDescription(transferFromTo.getDescription());
        } else {
            newTransaction.setDescription("");
        }
        if (transferFromTo.getRecipientName() != null) {
            newTransaction.setRecipientName(transferFromTo.getRecipientName());
        } else {
            newTransaction.setRecipientName("");
        }

        newTransaction.setUserFrom(transferFromTo.getAccountNumberFrom());
        newTransaction.setUserTo(transferFromTo.getAccountNumberTo());
        newTransaction.setAmount(Math.round(transferFromTo.getAmount() * 100.0) / 100.0);
        newTransaction.setType("Transfer");
        transactionRepository.save(newTransaction);

    }

    public void addNewDepositTransaction(TransferFromTo transferFromTo) {
        transferFromTo.setAccountNumberFrom("BANK1337");
        Transaction newTransaction = new Transaction();
        if (transferFromTo.getDescription() != null) {
            newTransaction.setDescription(transferFromTo.getDescription());
        } else {
            newTransaction.setDescription("Deposit to " + transferFromTo.getAccountNumberTo());
        }
        if (transferFromTo.getRecipientName() != null) {
            newTransaction.setRecipientName(transferFromTo.getRecipientName());
        } else {
            newTransaction.setRecipientName(transferFromTo.getAccountNumberTo() + " Account");
        }
        newTransaction.setUserTo(transferFromTo.getAccountNumberTo());
        newTransaction.setUserFrom(transferFromTo.getAccountNumberFrom());
        newTransaction.setAmount(Math.round(transferFromTo.getAmount() * 100.0) / 100.0);
        newTransaction.setType("Deposit");
        transactionRepository.save(newTransaction);

    }

    public void addNewWithdrawTransaction(TransferFromTo transferFromTo) {
        Transaction newTransaction = new Transaction();

        newTransaction.setUserFrom(transferFromTo.getAccountNumberFrom());
        if (transferFromTo.getAccountNumberTo() != null) {
            newTransaction.setUserTo(transferFromTo.getAccountNumberTo());
        } else {
            newTransaction.setUserTo("");
        }
        if (transferFromTo.getDescription() != null) {
            newTransaction.setDescription(transferFromTo.getDescription());
        } else {
            newTransaction.setDescription("Withdraw from " + transferFromTo.getAccountNumberFrom());
        }
        if (transferFromTo.getRecipientName() != null) {
            newTransaction.setRecipientName(transferFromTo.getRecipientName());
        } else {
            newTransaction.setRecipientName("");
        }

        newTransaction.setAmount(Math.round(transferFromTo.getAmount() * 100.0) / 100.0);
        newTransaction.setType("Withdraw");
        transactionRepository.save(newTransaction);

    }

    public void addNewFailedTransaction(TransferFromTo transferFromTo) {
        Transaction newTransaction = new Transaction();
        if (transferFromTo.getAccountNumberFrom() != null) {
            newTransaction.setUserFrom(transferFromTo.getAccountNumberFrom());
        } else {
            newTransaction.setUserFrom("");
        }
        if (transferFromTo.getAccountNumberTo() != null) {
            newTransaction.setUserTo(transferFromTo.getAccountNumberTo());
        } else {
            newTransaction.setUserTo("");
        }
        if (transferFromTo.getDescription() != null) {
            newTransaction.setDescription(transferFromTo.getDescription());
        } else {
            newTransaction.setDescription("");
        }
        if (transferFromTo.getRecipientName() != null) {
            newTransaction.setRecipientName(transferFromTo.getRecipientName());
        } else {
            newTransaction.setRecipientName("");
        }
        newTransaction.setAmount(Math.round(transferFromTo.getAmount() * 100.0) / 100.0);
        newTransaction.setType("Failed - not enough funds");
        transactionRepository.save(newTransaction);

    }

    public void deleteTransaction(long transactionId) {
        boolean exists = transactionRepository.existsById(transactionId);
        if (!exists) {
            throw new IllegalStateException(("Transaction with id " + transactionId + " does not exist."));
        }
        transactionRepository.deleteById(transactionId);
    }

    public class TransferFromTo {
        public double amount;
        public String accountNumberFrom;
        public String accountNumberTo;
        public String type;
        public String recipientName;
        public String description;

        public TransferFromTo(double amount, String accountNumberFrom, String accountNumberTo, String type, String recipientName, String description) {
            this.amount = amount;
            this.accountNumberFrom = accountNumberFrom;
            this.accountNumberTo = accountNumberTo;
            this.type = type;
            this.recipientName = recipientName;
            this.description = description;
        }

        public TransferFromTo(String accountNumberFrom) {
            this.accountNumberFrom = accountNumberFrom;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAccountNumberFrom() {
            return accountNumberFrom;
        }

        public void setAccountNumberFrom(String accountNumberFrom) {
            this.accountNumberFrom = accountNumberFrom;
        }

        public String getAccountNumberTo() {
            return accountNumberTo;
        }

        public void setAccountNumberTo(String accountNumberTo) {
            this.accountNumberTo = accountNumberTo;
        }

        public String getRecipientName() {
            return recipientName;
        }

        public void setRecipientName(String recipientName) {
            this.recipientName = recipientName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public TransferFromTo() {
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

    }

}
