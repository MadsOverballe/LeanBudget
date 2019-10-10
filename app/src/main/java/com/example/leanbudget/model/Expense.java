package com.example.leanbudget.model;

import java.util.Date;

public class Expense {

    private String description;
    private double cost;
    private Category category;
    private Date datetime;

    public Expense(String description, double cost, Category category, Date datetime) {
        this.description = description;
        this.cost = cost;
        this.category = category;
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
