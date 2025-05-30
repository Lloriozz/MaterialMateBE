package com.mm.mm.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ExchangeInfo")
public class ExchangeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExchangeID")
    private Integer exchangeID;

    @Column(name = "Type")
    private String type;

    @Column(name = "Date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "StudentID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "ItemID")
    private Item item;

    // Getters and Setters

    public Integer getExchangeID() {
        return exchangeID;
    }

    public void setExchangeID(Integer exchangeID) {
        this.exchangeID = exchangeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
