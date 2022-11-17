package com.rihardsedmundscerps.abilitypay.items;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

@JsonDeserialize(builder = TransactionItem.Builder.class)
public class TransactionItem {

    private long id;
    private String type;
    private LocalDateTime date;
    private String userFrom;
    private String userTo;
    private double amount;
    private String recipientName;
    private String description;

    private TransactionItem(TransactionItem.Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.date = builder.date;
        this.userFrom = builder.userFrom;
        this.userTo = builder.userTo;
        this.amount = builder.amount;
        this.recipientName = builder.recipientName;
        this.description = builder.description;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public double getAmount() {
        return amount;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionItem that = (TransactionItem) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && Objects.equals(type, that.type) && Objects.equals(date, that.date) && Objects.equals(userFrom, that.userFrom) && Objects.equals(userTo, that.userTo) && Objects.equals(recipientName, that.recipientName) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, date, userFrom, userTo, amount, recipientName, description);
    }

    @Override
    public String toString() {
        return "TransactionItem{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", userFrom='" + userFrom + '\'' +
                ", userTo='" + userTo + '\'' +
                ", amount=" + amount +
                ", recipientName='" + recipientName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {

        private long id;
        private String type;
        private LocalDateTime date;
        private String userFrom;
        private String userTo;
        private double amount;
        private String recipientName;
        private String description;

        public TransactionItem.Builder id(long id) {
            this.id = id;
            return this;
        }

        public TransactionItem.Builder type(String type) {
            this.type = type;
            return this;
        }

        public TransactionItem.Builder date(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public TransactionItem.Builder userFrom(String userFrom) {
            this.userFrom = userFrom;
            return this;
        }

        public TransactionItem.Builder userTo(String userTo) {
            this.userTo = userTo;
            return this;
        }

        public TransactionItem.Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public TransactionItem.Builder recipientName(String recipientName) {
            this.recipientName = recipientName;
            return this;
        }

        public TransactionItem.Builder description(String description) {
            this.description = description;
            return this;
        }

        public TransactionItem build() {
            return new TransactionItem(this);
        }

    }

}
