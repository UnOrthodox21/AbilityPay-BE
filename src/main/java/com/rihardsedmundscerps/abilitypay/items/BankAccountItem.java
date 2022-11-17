package com.rihardsedmundscerps.abilitypay.items;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

@JsonDeserialize(builder = BankAccountItem.Builder.class)
public class BankAccountItem {

    private long id;
    private String number;
    private String type;
    private double balance;
    private Long userId;

    private BankAccountItem(BankAccountItem.Builder builder) {
        this.id = builder.id;
        this.number = builder.number;
        this.type = builder.type;
        this.balance = builder.balance;
        this.userId = builder.userId;
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountItem that = (BankAccountItem) o;
        return id == that.id && Double.compare(that.balance, balance) == 0 && Objects.equals(number, that.number) && Objects.equals(type, that.type) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, type, balance, userId);
    }

    @Override
    public String toString() {
        return "BankAccountItem{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                ", userId=" + userId +
                '}';
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private long id;
        private String number;
        private String type;
        private double balance;
        private Long userId;

        public BankAccountItem.Builder id(long id) {
            this.id = id;
            return this;
        }

        public BankAccountItem.Builder number(String number) {
            this.number = number;
            return this;
        }

        public BankAccountItem.Builder type(String type) {
            this.type = type;
            return this;
        }

        public BankAccountItem.Builder balance(double balance) {
            this.balance = balance;
            return this;
        }

        public BankAccountItem.Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public BankAccountItem build() {
            return new BankAccountItem(this);
        }

    }

}
