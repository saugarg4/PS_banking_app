package org.example;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientClass {

    BufferedReader buff;
    InputStreamReader isr;

    PrintWriter pw;

    Socket ss;

    public static void main(String args[]) throws Exception{
        ClientClass cc = new ClientClass();
        cc.ss = new Socket("127.0.0.1", 8989);
        cc.isr = new InputStreamReader(cc.ss.getInputStream());
        cc.buff = new BufferedReader(cc.isr);

        cc.pw = new PrintWriter(cc.ss.getOutputStream(), true);

        cc.pw.println("Hello Server I am Harsh");
        System.out.println("Message from Server is "+ cc.buff.readLine());

        cc.buff.close();
        cc.pw.close();
    }

}
