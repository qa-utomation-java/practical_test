package com.practical.framework.entity;

/**
 * Created by sergey on 5/27/16.
 */

public class Payment {

    private Double amount;

    public Payment(){

    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                '}';
    }
}
