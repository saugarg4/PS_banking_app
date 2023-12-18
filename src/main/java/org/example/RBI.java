package org.example;

import java.io.BufferedReader;

public abstract class RBI {
    final float loanPercent = 1;
    final float creditCardPercent = 1;
    float totalProfit = 0;
    public abstract void depositMoney(Account account, float amount);

    public abstract void withdrawMoney(Account account, float amount);
    public abstract boolean isAccountPresent(String aadhar);
    public abstract Account getAccount(String aadhar);
    public abstract void getAccountDetails(String aadhar);
    public abstract void openBankFD(BufferedReader buff, Account account);
    public abstract void applyLoan(BufferedReader buff, Account account);
    public abstract void applyCreditCard(BufferedReader buff, Account account);
    public abstract float getMinBalance();
    public abstract void createBankAccount(BufferedReader buff);
}
