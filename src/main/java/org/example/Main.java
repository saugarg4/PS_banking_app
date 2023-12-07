package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    BufferedReader buff;
    InputStreamReader isr;
    Map<String, Customer> customerRecord;
    RBI rbi;
    boolean isAccountCreation;

    public Main() {
        if(isr == null)
            isr = new InputStreamReader(System.in);
        if(buff==null)
            buff = new BufferedReader(isr);
        if(customerRecord == null)
            customerRecord = new HashMap<>();
        if(rbi == null){
            rbi = new RBI();
        }
        isAccountCreation = false;
    }

    int selectedBank, selectedOperation;

    public boolean isCustomerPresent(String aadhar){
        return customerRecord.containsKey(aadhar);
    }

    public void deposit(){
        System.out.println("Enter your Aadhar Number");
        try{
            String aadhar = buff.readLine();
            if(isCustomerPresent(aadhar)){
                Customer cust = customerRecord.get(aadhar);
                float depositedAmount;
                System.out.println("Enter the amount you want to deposit: ");
                try{
                    depositedAmount = Float.parseFloat(buff.readLine());
                    rbi.depositMoney(cust, depositedAmount, isAccountCreation);
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
            if(isCustomerPresent(aadhar)){
                Customer cust = customerRecord.get(aadhar);
                float withdrawlAmount;
                System.out.println("Enter the amount you want to withdraw: ");
                try{
                    withdrawlAmount = Float.parseFloat(buff.readLine());
                    rbi.withdrawMoney(cust, withdrawlAmount);
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

    public void createAccount(){
        Customer cust = new Customer();
        isAccountCreation = true;
        System.out.println("Enter your Aadhar Number: ");
        try{
            String aadhar = buff.readLine();
            if(!isCustomerPresent(aadhar)){
                float amount;

                System.out.println("Minimum amount required to open the account is " + rbi.getMinBalance());
                System.out.println("Enter the amount you want to open your bank account with :");
                try{
                    amount = Float.parseFloat(buff.readLine());
                    rbi.depositMoney(cust, amount, isAccountCreation);
                    if(isAccountCreation){
                       System.out.println("Your account is successfully created.");
                       customerRecord.put(aadhar, cust);
                       isAccountCreation = false;
                    }
                }
                catch(IOException e){
                    e.printStackTrace();
                }

            }
            else{
                System.out.println("Sorry, your account is already registered.");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        Main obj = new Main();
        System.out.println("Welcome to IBS\nPlease select your bank\n1. ICICI\n2. HDFC\n3. SBI\n4. AXIS\n5. IDFC");

        try {
            obj.selectedBank = Integer.parseInt(obj.buff.readLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Customer Selected " + obj.selectedBank);

        while(true){
            System.out.println("Select your choice\n1. Deposit\n2. Withdrawl\n3. OpenFD\n4. Apply Loan\n5. Apply CC\n6. Create Account");
            try {
                obj.selectedOperation = Integer.parseInt(obj.buff.readLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Customer Selected " + obj.selectedOperation);
            switch(obj.selectedOperation){
                case 1:
                    obj.deposit(); break;

                case 2:
                    obj.withdrawl(); break;
                case 6:
                    obj.createAccount(); break;
                default:
                    System.out.println("Please enter the correct option");
            };
        }


    }
}