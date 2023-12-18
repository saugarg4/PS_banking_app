package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main extends Thread {
    BufferedReader buff;
    InputStreamReader isr;
    RBI rbi;
    String[] bankType;
    boolean isAccountCreation;
    boolean isUserContinue;
    List<RBI> bankList;

    public Main() {
        if(isr == null)
            isr = new InputStreamReader(System.in);
        if(buff==null)
            buff = new BufferedReader(isr);
        if(bankType == null){
            bankType = new String[]{"ICICI", "HDFC", "SBI", "Axis", "IDFC"};
        }
        isAccountCreation = false;
        if(bankList == null){
            bankList = new ArrayList<>();
            bankList.add(new ICICI());
            bankList.add(new HDFC());
            bankList.add(new SBI());
            bankList.add(new Axis());
            bankList.add(new IDFC());

        }
    }

    int selectedBank, selectedOperation;



    public void deposit(){
        System.out.println("Enter your Aadhar Number");
        try{
            String aadhar = buff.readLine();
            Account account = rbi.getAccount(aadhar);
            if(account != null){
                float depositedAmount;
                System.out.println("Enter the amount you want to deposit: ");
                try{
                    depositedAmount = Float.parseFloat(buff.readLine());
                    rbi.depositMoney(account, depositedAmount);
                }
                catch (IOException e){
                    e.printStackTrace();
                }

            }
            else{
                System.out.println("Sorry, your account is not registered.");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void withdrawl(){
        System.out.println("Enter your Aadhar Number: ");
        try{
            String aadhar = buff.readLine();
            Account account = rbi.getAccount(aadhar);
            if(account != null){
                float withdrawlAmount;
                System.out.println("Enter the amount you want to withdraw: ");
                try{
                    withdrawlAmount = Float.parseFloat(buff.readLine());
                    rbi.withdrawMoney(account, withdrawlAmount);
                }
                catch (IOException e){
                    e.printStackTrace();
                }

            }
            else{
                System.out.println("Sorry, your account is not registered.");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loan(){
        System.out.println("Enter your Aadhar Number: ");
        try{
            String aadhar = buff.readLine();
            Account account = rbi.getAccount(aadhar);
            if(account != null){
                rbi.applyLoan(buff, account);
            }
            else{
                System.out.println("Sorry, your account is not registered.");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void createCreditCard(){
        System.out.println("Enter your Aadhar Number: ");
        try{
            String aadhar = buff.readLine();
            Account account = rbi.getAccount(aadhar);
            if(account != null){
                rbi.applyCreditCard(buff, account);
            }
            else{
                System.out.println("Sorry, your account is not registered.");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void createAccount(){
        System.out.println("Enter your Aadhar Number: ");
        try{
            String aadhar = buff.readLine();
            Account account = rbi.getAccount(aadhar);
            if(account == null){
                rbi.createBankAccount(buff);
            }
            else{
                System.out.println("Sorry, your account is already present.");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void openFD(){
        System.out.println("Enter your Aadhar Number: ");
        try{
            String aadhar = buff.readLine();
            Account account = rbi.getAccount(aadhar);
            if(account != null){
                rbi.openBankFD(buff, account);
            }
            else{
                System.out.println("Sorry, your account is not registered.");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void getAccountDetails(){
        System.out.println("Enter your Aadhar Number: ");
        try{
            String aadhar = buff.readLine();
            Account account = rbi.getAccount(aadhar);
            if(account != null){
                rbi.getAccountDetails(aadhar);
            }
            else{
                System.out.println("Sorry, your account is not registered.");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main obj = new Main();
        obj.start();
    }


    public void run(){
        while(true){

            do{
                System.out.println("Welcome to IBS\nPlease select your bank\n1. ICICI\n2. HDFC\n3. SBI\n4. AXIS\n5. IDFC");
                try {
                    selectedBank = Integer.parseInt(buff.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }while(selectedBank < 1 && selectedBank > 5);
            System.out.println("Account Selected " + selectedBank);

            switch(selectedBank){
                case 1: rbi = bankList.get(0); break;
                case 2: rbi = bankList.get(1);break;
                case 3: rbi = bankList.get(2); break;
                case 4: rbi = bankList.get(3); break;
                case 5: rbi = bankList.get(4); break;
            }
            isUserContinue = true;
            while(isUserContinue){
                System.out.println("Select your choice\n1. Deposit\n2. Withdrawl\n3. OpenFD\n4. Apply Loan\n5. Apply CC\n6. Create Account\n7. Get Account Details\n8. Exit");
                try {
                    selectedOperation = Integer.parseInt(buff.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Account Selected " + selectedOperation);
                switch(selectedOperation){
                    case 1:
                        deposit(); break;
                    case 2:
                        withdrawl(); break;
                    case 3:
                        openFD(); break;
                    case 4:
                        loan(); break;
                    case 5:
                        createCreditCard();break;
                    case 6:
                        createAccount(); break;
                    case 7:
                        getAccountDetails(); break;
                    case 8:
                        isUserContinue = false; break;
                    default:
                        System.out.println("Please enter the correct option");
                };
            }
       }
    }
}

