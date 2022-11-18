package com.rihardsedmundscerps.abilitypay.services;

import com.rihardsedmundscerps.abilitypay.items.BankAccountItem;
import com.rihardsedmundscerps.abilitypay.mappers.BankAccountMapper;
import com.rihardsedmundscerps.abilitypay.models.BankAccount;
import com.rihardsedmundscerps.abilitypay.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;

    public BankAccountService(BankAccountRepository bankAccountRepository, BankAccountMapper bankAccountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountMapper = bankAccountMapper;
    }

    public void addNewBankAccount(BankAccountItem bankAccountItem) {
        BankAccount bankAccountToSave = bankAccountMapper.toBankAccount(bankAccountItem);
        bankAccountRepository.save(bankAccountToSave);
    }

    public List<BankAccountItem> getAllBankAccounts() {
        List<BankAccount> allBankAccounts = bankAccountRepository.findAll();

        return allBankAccounts.stream()
                .map(bankAccountMapper::toBankAccountItem)
                .collect(Collectors.toList());
    }

    public Optional<BankAccountItem> getBankAccountById(Long bankAccountId) {
        Optional<BankAccount> foundBankAccount = bankAccountRepository.findById(bankAccountId);
        BankAccountItem foundBankAccountItem = bankAccountMapper.toBankAccountItem(foundBankAccount.get());
        return Optional.of(foundBankAccountItem);
    }

    public void editBankAccountByAccountNumber(BankAccountItem bankAccountItem, String accountNumber){
        Optional<BankAccountItem> bankAccountItemToPut = getBankAccountByAccountNumber(accountNumber);
        BankAccount bankAccountToPut = bankAccountMapper.toBankAccount(bankAccountItemToPut.get());

        if(bankAccountItem.getBalance() != 0){
            bankAccountToPut.setBalance(bankAccountItem.getBalance());
        }

        if(bankAccountItem.getNumber() != ""){
            bankAccountToPut.setNumber(bankAccountItem.getNumber());
        }

        if(bankAccountItem.getType() != ""){
            bankAccountToPut.setType(bankAccountItem.getType());
        }

        bankAccountRepository.save(bankAccountToPut);
    }

    public Optional<BankAccountItem> getBankAccountByAccountNumber(String accountNumber){
        Optional<BankAccount> foundBookRequestEntity = bankAccountRepository.getBankAccountByNumber(accountNumber);
        BankAccountItem foundBookRequestItem = bankAccountMapper.toBankAccountItem(foundBookRequestEntity.get());
        return Optional.of(foundBookRequestItem);
    }

    public List<BankAccountItem> getBankAccountsByUserId(Long userId) {
        List<BankAccount> foundBankAccounts = bankAccountRepository.getBankAccountsByUserId(userId);

        List<BankAccountItem> foundBankAccountItems =  foundBankAccounts.stream()
                .map(bankAccount -> bankAccountMapper.toBankAccountItem(bankAccount))
                .collect(Collectors.toList());

        return foundBankAccountItems;
    }

    public boolean sendFunds(TransactionService.TransferFromTo transferFromTo) {
        List<BankAccountItem> bankAccountItemList = getAllBankAccounts();

        String nameFrom = transferFromTo.getAccountNumberFrom();
        String nameTo = transferFromTo.getAccountNumberTo();
        BankAccount bankAccountFrom = null;
        BankAccount bankAccountTo = null;

        for (int i = 0; i < bankAccountItemList.size(); i++) {
            if (bankAccountItemList.get(i).getNumber().equals(nameFrom)) {
                bankAccountFrom = bankAccountMapper.toBankAccount(bankAccountItemList.get(i));

            }
            if (bankAccountItemList.get(i).getNumber().equals(nameTo)) {
                bankAccountTo = bankAccountMapper.toBankAccount(bankAccountItemList.get(i));
            }
        }

        if(bankAccountFrom.getBalance()>=transferFromTo.getAmount()){
            bankAccountFrom.setBalance(Math.round((bankAccountFrom.getBalance() - transferFromTo.getAmount()) * 100.0) / 100.0);
            bankAccountTo.setBalance(Math.round((bankAccountTo.getBalance() + transferFromTo.getAmount()) * 100.0) / 100.0);
            bankAccountRepository.save(bankAccountFrom);
            bankAccountRepository.save(bankAccountTo);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean withdrawFunds(TransactionService.TransferFromTo transferFromTo){
        List<BankAccountItem> bankAccountItemList = getAllBankAccounts();

        String nameFrom = transferFromTo.getAccountNumberFrom();
        BankAccount bankAccountFrom = null;

        for (int i = 0; i < bankAccountItemList.size(); i++) {
            if (bankAccountItemList.get(i).getNumber().equals(nameFrom)) {
                bankAccountFrom = bankAccountMapper.toBankAccount(bankAccountItemList.get(i));

            }
        }
        if(bankAccountFrom.getBalance()>=transferFromTo.getAmount()){
            bankAccountFrom.setBalance(Math.round((bankAccountFrom.getBalance() - transferFromTo.getAmount()) * 100.0) / 100.0);
            bankAccountRepository.save(bankAccountFrom);
            return true;
        }
        else{
            return false;

        }

    }

    public boolean depositFunds(TransactionService.TransferFromTo transferFromTo) {
        List<BankAccountItem> bankAccountItemList = getAllBankAccounts();

        String nameFrom = "BANK1337";
        String nameTo = transferFromTo.getAccountNumberTo();
        BankAccount bankAccountFrom = null;
        BankAccount bankAccountTo = null;

        for (int i = 0; i < bankAccountItemList.size(); i++) {
            if (bankAccountItemList.get(i).getNumber().equals(nameFrom)) {
                bankAccountFrom = bankAccountMapper.toBankAccount(bankAccountItemList.get(i));

            }
            if (bankAccountItemList.get(i).getNumber().equals(nameTo)) {
                bankAccountTo = bankAccountMapper.toBankAccount(bankAccountItemList.get(i));

            }
        }

        if (bankAccountFrom.getBalance() >= transferFromTo.getAmount()) {
            bankAccountFrom.setBalance(Math.round((bankAccountFrom.getBalance() - transferFromTo.getAmount()) * 100.0) / 100.0);
            bankAccountTo.setBalance(Math.round((bankAccountTo.getBalance() + transferFromTo.getAmount()) * 100.0) / 100.0);
            bankAccountRepository.save(bankAccountFrom);
            bankAccountRepository.save(bankAccountTo);
            return true;
        } else {
            return false;
        }
    }

    public void deleteBankAccount(String bankAccountNumber) {
        Optional<BankAccountItem> bankAccountItemToDelete = getBankAccountByAccountNumber(bankAccountNumber);
        Long id = bankAccountItemToDelete.get().getId();
        bankAccountRepository.deleteById(id);
    }
}
