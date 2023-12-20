package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SBI extends RBI{

    final String[] loanType = {"home", "education", "personal", "car"}; ;
    final float[] loanBankPercent = {5, 3, 6, 8};
    final float creditCardBankPercent = 2;
    final float minBalance = 1000;
    final float ROI = 5;
    final Logger log;
    private Map<String, Account> customerAccountRecord;
    private float bankProfit;
    public SBI(){
        bankProfit = 0;
        if(customerAccountRecord == null)
            customerAccountRecord = new HashMap<>();
        log = Logging.getInstance().getLog();
    }

    public boolean isAccountPresent(String aadhar){
        return customerAccountRecord.containsKey(aadhar);
    }

    public Account getAccount(String aadhar){
        Account account = null;
        if(isAccountPresent(aadhar)){
            account = customerAccountRecord.get(aadhar);
        }
        return account;
    }

    public void setCustomerAccount(String aadhar, Account account){
        customerAccountRecord.put(aadhar, account);
    }
    public void depositMoney(Account account, float amount) {
        if(amount > 0){
            account.setBalance(account.getBalance() + amount);
            log.log(Level.INFO,"Your amount is successfully added");
        }
        else{
            log.log(Level.WARNING, "The depositing amount cannot be negative or Zero");
        }
        System.out.println("Net balance: " + account.getBalance());
    }

    public void withdrawMoney(Account account, float amount){
        if(amount > 0){
            float currentBalance = account.getBalance();
            if(currentBalance - amount >= minBalance){
                account.setBalance(currentBalance - amount);
            }
            else{
                System.out.println("There has to be minBalance of " + minBalance);
            }
        }
        else{
            log.log(Level.WARNING, "The withdrawing amount cannot be negative or Zero");
        }
        System.out.println("Net balance: " + account.getBalance());
    }

    public float getBankProfit() {
        return bankProfit;
    }

    public void openBankFD(BufferedReader buff, Account account) {
        float FDAmount = 0;
        do {
            System.out.println("Please, enter the FD Amount: ");
            try {
                FDAmount = Float.parseFloat(buff.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (FDAmount <= 0) {
                System.out.println("Please, enter the correct amount");
            }
        } while (FDAmount <= 0);
        int years = 0;
        do {
            System.out.println("Please, enter the time period of FD: ");
            try {
                years = Integer.parseInt(buff.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (years <= 0) {
                System.out.println("Please, enter the correct time period");
            }
        } while (years <= 0);
        float currAmount = FDAmount;
        for(int i =1; i<=years; i++){
            currAmount = currAmount*(1 + ROI/100);
            System.out.println("Amount for " + i + " is: " + currAmount);
        }
        account.setFDAmount(account.getFDAmount() + currAmount);
        float totalProfit = currAmount - FDAmount;
        System.out.println("Total Profit: " + totalProfit);
    }

    public boolean isLoanApplicable(Account account){
        return account.getBalance() > (minBalance*2) && (account.getLoanType() == -1) ;
    }
    public boolean isCreditApplicable(Account account){
        return account.getBalance() > (minBalance*2) && (account.getCreditCardMaxLimit() == 0) ;
    }
    public void applyLoan(BufferedReader buff, Account account) {
        if(isLoanApplicable(account)) {

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
                    amount = Float.parseFloat(buff.readLine());
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
            float loanInterest = 0;
            for(int i =1; i<= years; i++){
                amount += loanInterest;
                loanInterest = amount * loanBankPercent[loanType - 1] * i / 100;
                totalProfit += loanInterest * loanPercent / 100;
                bankProfit += loanInterest*(loanBankPercent[loanType - 1] - loanPercent)/100;
                System.out.println("Your loan interest amount for " + i + " year will be " + loanInterest);

            }
            account.setLoanType(loanType -1);
            account.setLoanAmount(amount);
            System.out.println("Total amount to be paid is " + amount);
        }
        else{
            log.log(Level.WARNING, "Sorry, your balance is not sufficient to grant you a loan");
        }
    }
    public Customer customerDialog(BufferedReader buff) {
        String line;
        Customer customer = new Customer();
        try {
            System.out.print("Enter your name: ");
            do {
                line = buff.readLine();
            } while (line.length() == 0);
            customer.setCustomerName(line.toLowerCase().trim());
            line = "";
            System.out.print("Enter your aadhar number  : ");
            do {
                line = buff.readLine();
            } while (line.length() == 0);
            customer.setCustomerAadhar(line);
            line = "";
            System.out.print("Enter your email id  : ");
            do {
                line = buff.readLine();
            } while (line.length() == 0);
            customer.setCustomerEmail(line);
            line = "";
            System.out.print("Enter your address: ");
            do {
                line = buff.readLine();
            } while (line.length() == 0);
            customer.setCustomerAddress(line);
            line = "";
            System.out.print("Enter your gender: ");
            do {
                line = buff.readLine();
            } while (line.length() == 0);
            customer.setCustomerGender(line);
            line = "";
            System.out.print("Enter your phone number: ");
            do {
                line = buff.readLine();
            } while (line.length() == 0);
            customer.setCustomerPhone(line);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return customer;
    }
    public void createBankAccount(BufferedReader buff){
        Account account = new Account(customerDialog(buff));
        float amount = 0;
        do{
            System.out.println("Minimum amount required to open the account is " + minBalance);
            System.out.println("Enter the amount you want to open your bank account with :");
            try{
                amount = Float.parseFloat(buff.readLine());
                if(amount >= minBalance){
                    account.setBalance(amount);
                    log.log(Level.INFO, "Your account is successfully created.");
                }
                else{
                    log.log(Level.WARNING, "Please, enter the valid amount");

                }
            } catch(IOException e){
                e.printStackTrace();
            }

        }while(amount < minBalance);

        setCustomerAccount(account.getCustomer().getCustomerAadhar(), account);

    }
    public void applyCreditCard(BufferedReader buff, Account account) {
        if(isCreditApplicable(account)) {
            float creditLimit = 0;
            do {
                System.out.println("Please, enter the max limit of credit card");
                try {
                    creditLimit = Integer.parseInt(buff.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (creditLimit <= 0) {
                    System.out.println("Please, enter the correct amount");
                }
            } while (creditLimit <= 0);
            float creditServiceCharge = account.getBalance() * creditCardBankPercent / 100;
            totalProfit += account.getBalance() * creditCardPercent  / 100;
            bankProfit += account.getBalance() * (creditCardBankPercent - creditCardPercent)/100;
            log.log(Level.INFO, "Your credit card is issued\nThank you");
            System.out.println("Service charges to be paid is " + creditServiceCharge);
            account.setBalance(account.getBalance() - creditServiceCharge);
            account.setCreditCardMaxLimit(creditLimit);
            System.out.println("Net balance: " + account.getBalance());

        }
        else{
            log.log(Level.WARNING, "Sorry, your balance is not sufficient to grant you a credit card");
        }
    }

    public void getAccountDetails(String aadhar){
        Account account = getAccount(aadhar);
        System.out.println("Customer name: " + account.getCustomer());
        System.out.println("Customer aadhar number: " + account.getCustomer().getCustomerAadhar());
        System.out.println("Customer email : " + account.getCustomer().getCustomerEmail());
        System.out.println("Customer Address: " + account.getCustomer().getCustomerAddress());
        System.out.println("Customer name: " + account.getCustomer().getCustomerName());
        System.out.println("Customer Gender: " + account.getCustomer().getCustomerGender());
        System.out.println("Customer Phone Number: " + account.getCustomer().getCustomerPhone());

        System.out.println("Customer balanace: " + account.getBalance());
        System.out.println("Customer FDAmount: " + account.getFDAmount());
        if(account.getLoanType() > -1){
            System.out.println("Customer LoanAmount: " + account.getLoanAmount());
            System.out.println("Customer LoanType: " + loanType[account.getLoanType()]);
        }
        System.out.println("Customer Credit card max limit: " + account.getCreditCardMaxLimit());


    }
    public float getMinBalance() {
        return minBalance;
    }


}
