package org.example;
public class RBI {
    private float minBalance;

    public RBI(){
        minBalance = 1000;
    }

    public void depositMoney(Customer obj, float amount, boolean isAccountCreation) {
        if(isAccountCreation){
            if(amount >= minBalance){
                obj.setBalance(obj.getBalance() + amount);
                System.out.println("Your account is successfully created");
            }
            else{
                System.out.println("Sorry, minimum amount required to open the account is " + minBalance);
                isAccountCreation = false;
                return;
            }
        }

        else if(amount > 0){
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

    public void openFD(float amount, float ROI, int years) {
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
    public void applyLoan(String loanType, float amount, float ROI, int years) {}
    public void applyCreditCard() {}
    public float getMinBalance() {
        return minBalance;
    }
}
