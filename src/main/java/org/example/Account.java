package org.example;

public class Account {
    private Customer customer;
    private float balance;
    private float FDAmount;
    private float loanAmount;
    private int loanType;
    private float creditCardMaxLimit;

    public Account(){
        customer = new Customer();
        balance = 0;
        FDAmount = 0;
        loanAmount = 0;
        loanType = -1;
        creditCardMaxLimit = 0;
    }

    public Account(String customerName, String customerEmail, String customerAddress, String customerGender, String customerAadhar, String customerPhone, float balance){
        customer = new Customer(customerName,customerEmail,customerAddress, customerGender,customerAadhar,customerPhone);
        this.balance = balance;

    }
    public Account(Customer customer){
        this.customer = customer;
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public Customer getCustomer(){
        return customer;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    public float getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(float loanAmount) {
        this.loanAmount = loanAmount;
    }
    public float getFDAmount() {
        return FDAmount;
    }

    public void setFDAmount(float FDAmount) {
        this.FDAmount = FDAmount;
    }
    public float getCreditCardMaxLimit() {
        return creditCardMaxLimit;
    }

    public void setCreditCardMaxLimit(float creditCardMaxLimit) {
        this.creditCardMaxLimit = creditCardMaxLimit;
    }

    public int getLoanType() {
        return loanType;
    }

    public void setLoanType(int loanType) {
        this.loanType = loanType;
    }
}
