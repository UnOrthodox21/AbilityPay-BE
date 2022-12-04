package com.rihardsedmundscerps.abilitypay.services;

import com.rihardsedmundscerps.abilitypay.items.BankAccountItem;
import com.rihardsedmundscerps.abilitypay.mappers.BankAccountMapper;
import com.rihardsedmundscerps.abilitypay.models.BankAccount;
import com.rihardsedmundscerps.abilitypay.repositories.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
public class BankAccountServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private BankAccountMapper bankAccountMapper;

    @InjectMocks
    private BankAccountService bankAccountService;

    private BankAccount bankAccount;
    private BankAccountItem bankAccountItem;

    @BeforeEach
    public void setup() {
        bankAccount = getBankAccount();
        bankAccountItem = getBankAccountItem();
    }

    @Test
    void testGetAllBankAccounts() {
        // Setup
        List<BankAccount> bankAccountList = new ArrayList<BankAccount>();
        bankAccountList.add(bankAccount);

        // Mock
        when(bankAccountRepository.findAll()).thenReturn(bankAccountList);
        when(bankAccountMapper.toBankAccount(bankAccountItem)).thenReturn(bankAccount);
        when(bankAccountMapper.toBankAccountItem(bankAccount)).thenReturn(bankAccountItem);

        // Perform
        List<BankAccountItem> foundBankAccountItemList = bankAccountService.getAllBankAccounts();
        BankAccountItem firstFoundBankAccountItem = foundBankAccountItemList.get(0);

        // Assert
        assert (foundBankAccountItemList.size() == 1);
        assert (firstFoundBankAccountItem.getId() == bankAccountItem.getId());
        assert (firstFoundBankAccountItem.getUserId()).equals(bankAccountItem.getUserId());
        assert (firstFoundBankAccountItem.getType()).equals(bankAccountItem.getType());
        assert (firstFoundBankAccountItem.getNumber()).equals(bankAccountItem.getNumber());
        assert (firstFoundBankAccountItem.getBalance() == bankAccountItem.getBalance());
    }

    @Test
    void testGetBankAccountById() {
        // Mock
        when(bankAccountRepository.findById(bankAccount.getId())).thenReturn(Optional.of(bankAccount));
        when(bankAccountMapper.toBankAccount(bankAccountItem)).thenReturn(bankAccount);
        when(bankAccountMapper.toBankAccountItem(bankAccount)).thenReturn(bankAccountItem);

        // Perform
        Optional<BankAccountItem> foundBankAccountItem = bankAccountService.getBankAccountById(bankAccount.getId());

        // Assert
        assert(foundBankAccountItem.get().getId() == bankAccountItem.getId());
        assert(foundBankAccountItem.get().getUserId()).equals(bankAccountItem.getUserId());
        assert(foundBankAccountItem.get().getType()).equals(bankAccountItem.getType());
        assert(foundBankAccountItem.get().getNumber()).equals(bankAccountItem.getNumber());
        assert(foundBankAccountItem.get().getBalance() == bankAccountItem.getBalance());

        // Verify
        verify(bankAccountRepository, times(1)).findById(bankAccount.getId());
    }

    // Helper functions

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
