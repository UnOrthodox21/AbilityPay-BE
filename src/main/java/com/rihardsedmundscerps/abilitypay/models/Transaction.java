package com.rihardsedmundscerps.abilitypay.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "date")
    @CreationTimestamp
    private LocalDateTime date;

    @Column(name = "user_from")
    private String userFrom;

    @Column(name = "user_to")
    private String userTo;

    @Column(name = "amount")
    private double amount;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "description")
    private String description;


    public Transaction() {}

    public Transaction(long id, String type, LocalDateTime date, String userFrom, String userTo, double amount) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.amount = amount;
    }

    public Transaction(long id, String type, LocalDateTime date, String userFrom, String userTo, double amount, String recipientName, String description) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.amount = amount;
        this.recipientName = recipientName;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", userFrom=" + userFrom +
                ", userTo=" + userTo +
                ", amount=" + amount +
                '}';
    }
}
