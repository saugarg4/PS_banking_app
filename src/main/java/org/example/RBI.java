package org.example;

import java.io.BufferedReader;

public abstract class RBI {
    final float loanPercent = 1;
    final float creditCardPercent = 2;
    float totalProfit = 0;
    public abstract void depositMoney(Customer obj, float amount);

    public abstract void withdrawMoney(Customer obj, float amount);

    public abstract void openFD(float amount, int years);
    public abstract void applyLoan(BufferedReader buff, Customer cust);
    public abstract void applyCreditCard();
    public abstract float getMinBalance();
    public abstract Customer createAccount(BufferedReader buff, String aadhar);
}
