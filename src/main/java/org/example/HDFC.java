package org.example;

import java.io.BufferedReader;
import java.io.IOException;

public class HDFC extends RBI{

    final String[] loanType = {"home", "education", "personal", "car"}; ;
    final float[] loanBankPercent = {5, 3, 6, 8};
    final float creditCardBankPercent = 2;
    final float minBalance = 1000;
    final float ROI = 5;
    private float bankProfit;
    public HDFC(){
        bankProfit = 0;
    }

    public void depositMoney(Customer obj, float amount) {
        if(amount > 0){
            obj.setBalance(obj.getBalance() + amount);
            System.out.println("Your amount is successfully added");
        }
        else{
            System.out.println("The depositing amount cannot be negative or Zero");
        }
        System.out.println("Net balance: " + obj.getBalance());
    }

    public void withdrawMoney(Customer obj, float amount){
        if(amount > 0){
            float currentBalance = obj.getBalance();
            if(currentBalance - amount >= minBalance){
                obj.setBalance(currentBalance - amount);
            }
            else{
                System.out.println("There has to be minBalance of " + minBalance);
            }
        }
        else{
            System.out.println("The withdrawing amount cannot be negative or Zero");
        }
        System.out.println("Net balance: " + obj.getBalance());
    }

    public float getBankProfit() {
        return bankProfit;
    }

    public void openFD(float amount, int years) {
        if(amount > 0){
            float currAmount = amount;
            for(int i =1; i<=years; i++){
                currAmount = currAmount*(1 + ROI/100);
                System.out.println("Amount for " + i + " is: " + currAmount);
            }
            float totalProfit = currAmount - amount;
            System.out.println("Total Profit: " + totalProfit);
        }
        else {
            System.out.println("Principle amount cannot be negative or Zero");
        }
    }

    public boolean isLoanApplicable(Customer cust){
        return cust.getBalance() > (minBalance*2);
    }
    public void applyLoan(BufferedReader buff, Customer cust) {
        if(isLoanApplicable(cust)) {

            int loanType = 1;
            do {
                System.out.println("Please enter loan type\n1: Home\n2: Education\n3: Personal\n4: Car\n");
                try {
                    loanType = Integer.parseInt(buff.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } while(loanType < 1 && loanType > 4);
            float amount = 0;
            do {
                System.out.println("Please, enter the loan amount");
                try {
                    amount = Integer.parseInt(buff.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (amount <= 0) {
                    System.out.println("Please, enter the correct amount");
                }
            } while (amount <= 0);
            int years = 0;
            do {
                System.out.println("Please, enter the tenure of loan");
                try {
                    years = Integer.parseInt(buff.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (years <= 0) {
                    System.out.println("Please, enter the correct years");
                }
            } while (years <= 0);

            float loanInterest = amount * loanBankPercent[loanType - 1] * years / 100;
            totalProfit += loanInterest * loanPercent / 100;
            System.out.println("Your loan interest amount will be " + loanInterest);
            System.out.println("Total amount to be paid is " + (amount + loanInterest));
        }
        else{
             System.out.println("Sorry, your balance is not sufficient to grant you a loan");
        }
    }
    public Customer createAccount(BufferedReader buff, String aadhar){
        Customer cust = new Customer();
        cust.setCustomerAadhar(aadhar);
        float amount;
        System.out.println("Minimum amount required to open the account is " + minBalance);
        System.out.println("Enter the amount you want to open your bank account with :");
        try{
              amount = Float.parseFloat(buff.readLine());
              if(amount > minBalance){
                  cust.setBalance(amount);
              }
              else{
                   System.out.println("Please, enter the valid amount");
              }
                   System.out.println("Your account is successfully created.");
              }
              catch(IOException e){
                   e.printStackTrace();
              }
        return cust;

    }
    public void applyCreditCard() {}
    public float getMinBalance() {
        return minBalance;
    }


}
