package com.rihardsedmundscerps.abilitypay.models;


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
