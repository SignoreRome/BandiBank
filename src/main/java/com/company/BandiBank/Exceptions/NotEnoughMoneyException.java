package com.company.BandiBank.Exceptions;

public class NotEnoughMoneyException extends Exception{
    private int amount;

    public NotEnoughMoneyException(){}

    public NotEnoughMoneyException(String str){
        super(str);
    }

    public NotEnoughMoneyException(String message, int amount) {
        super(message);
        this.amount = amount;
    }
}
