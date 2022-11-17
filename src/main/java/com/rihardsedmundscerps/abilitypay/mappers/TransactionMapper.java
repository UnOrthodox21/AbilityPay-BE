package com.rihardsedmundscerps.abilitypay.mappers;

import com.rihardsedmundscerps.abilitypay.items.TransactionItem;
import com.rihardsedmundscerps.abilitypay.models.Transaction;

public class TransactionMapper {

    public TransactionItem toTransactionItem(Transaction transaction) {

        if (transaction == null) {
            return null;
        }

        return new TransactionItem.Builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .recipientName(transaction.getRecipientName())
                .date(transaction.getDate())
                .description(transaction.getDescription())
                .userFrom(transaction.getUserFrom())
                .userTo(transaction.getUserTo())
                .amount(transaction.getAmount())
                .build();
    }

    public Transaction toTransaction(TransactionItem transactionItem) {

        if (transactionItem == null) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setId(transactionItem.getId());
        transaction.setType(transactionItem.getType());
        transaction.setRecipientName(transactionItem.getRecipientName());
        transaction.setDate(transactionItem.getDate());
        transaction.setDescription(transactionItem.getDescription());
        transaction.setUserFrom(transactionItem.getUserFrom());
        transaction.setUserTo(transactionItem.getUserTo());
        transaction.setAmount(transactionItem.getAmount());

        return transaction;
    }

}
