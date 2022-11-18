package com.rihardsedmundscerps.abilitypay.mappers;

import com.rihardsedmundscerps.abilitypay.items.BankAccountItem;
import com.rihardsedmundscerps.abilitypay.models.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {

    public BankAccountItem toBankAccountItem(BankAccount bankAccount) {

        if (bankAccount == null) {
            return null;
        }

        return new BankAccountItem.Builder()
                .id(bankAccount.getId())
                .userId(bankAccount.getUserId())
                .type(bankAccount.getType())
                .number(bankAccount.getNumber())
                .balance(bankAccount.getBalance())
                .build();
    }

    public BankAccount toBankAccount(BankAccountItem bankAccountItem) {

        if (bankAccountItem == null) {
            return null;
        }

        BankAccount bankAccount = new BankAccount();

        bankAccount.setId(bankAccountItem.getId());
        bankAccount.setUserId(bankAccountItem.getUserId());
        bankAccount.setType(bankAccountItem.getType());
        bankAccount.setNumber(bankAccountItem.getNumber());
        bankAccount.setBalance(bankAccountItem.getBalance());

        return bankAccount;
    }

}
