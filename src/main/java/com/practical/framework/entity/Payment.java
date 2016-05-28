package com.practical.framework.entity;

/**
 * Created by sergey on 5/27/16.
 */
public class Payment {

    private Double amount;
    private String error;

    public Payment() {

    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
