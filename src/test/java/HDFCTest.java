//package Java.Test;

import org.example.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

public class HDFCTest {
    static Account account;
    static RBI rbi;

    @BeforeAll
    public static  void init() {
       account = new Account();
       rbi = new HDFC();
    }

    @Test
    void depositMoneyTest() {
//        account = new Account();
        account.setBalance(1000);
//        rbi = new HDFC();
        rbi.depositMoney(account, 120);
        Assertions.assertEquals(1120, account.getBalance());
    }

    @Test
    void withdrawMoneyTest(){
//        account = new Account();
        account.setBalance(1000);
//        rbi = new HDFC();
        rbi.withdrawMoney(account,120);
        Assertions.assertEquals(1000, account.getBalance());
    }


}
