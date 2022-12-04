package com.rihardsedmundscerps.abilitypay.mappers;

import com.rihardsedmundscerps.abilitypay.items.BankAccountItem;
import com.rihardsedmundscerps.abilitypay.models.BankAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankAccountMapperTest {

    @Mock
    BankAccountMapper bankAccountMapper;

    @Test
    void testToBankAccountItem() {
        // Setup
        BankAccount bankAccount = getBankAccount();

        // Mock
        when(bankAccountMapper.toBankAccountItem(bankAccount)).thenReturn(getBankAccountItem());

        // Perform
        BankAccountItem bankAccountItem = bankAccountMapper.toBankAccountItem(bankAccount);

        // Verify
        assertThat(bankAccountItem.getId()).isEqualTo(bankAccount.getId());
        assertThat(bankAccountItem.getUserId()).isEqualTo(bankAccount.getUserId());
        assertThat(bankAccountItem.getType()).isEqualTo(bankAccount.getType());
        assertThat(bankAccountItem.getNumber()).isEqualTo(bankAccount.getNumber());
        assertThat(bankAccountItem.getBalance()).isEqualTo(bankAccount.getBalance());
    }

    @Test
    void testToBankAccount() {
        // Setup
        BankAccountItem bankAccountItem = getBankAccountItem();

        // Mock
        when(bankAccountMapper.toBankAccount(bankAccountItem)).thenReturn(getBankAccount());

        // Perform
        BankAccount bankAccount = bankAccountMapper.toBankAccount(bankAccountItem);

        // Verify
        assertThat(bankAccount.getId()).isEqualTo(bankAccountItem.getId());
        assertThat(bankAccount.getUserId()).isEqualTo(bankAccountItem.getUserId());
        assertThat(bankAccount.getType()).isEqualTo(bankAccountItem.getType());
        assertThat(bankAccount.getNumber()).isEqualTo(bankAccountItem.getNumber());
        assertThat(bankAccount.getBalance()).isEqualTo(bankAccountItem.getBalance());
    }

    // Helper Methods

    private BankAccount getBankAccount() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);
        bankAccount.setUserId(12345L);
        bankAccount.setType("Primary");
        bankAccount.setNumber("ABPAY12345");
        bankAccount.setBalance(12345.0);
        return bankAccount;
    }

    private BankAccountItem getBankAccountItem() {
        return new BankAccountItem
                .Builder()
                .id(1L)
                .userId(12345L)
                .type("Primary")
                .number("ABPAY12345")
                .balance(12345.0)
                .build();
    }
}