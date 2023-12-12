package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass {
    BufferedReader buff;
    InputStreamReader isr;

    PrintWriter pw;

    ServerSocket ss;

    Socket mSocket;

    public static void main(String args[]) throws Exception{
        ServerClass cc = new ServerClass();
        cc.ss = new ServerSocket(8989);
        System.out.println("I am Waiting for Client to Connect !!!!");
        cc.mSocket = cc.ss.accept();
        cc.isr = new InputStreamReader(cc.mSocket.getInputStream());
        cc.buff = new BufferedReader(cc.isr);

        cc.pw = new PrintWriter(cc.mSocket.getOutputStream(), true);
        System.out.println("Message from Client is "+ cc.buff.readLine());

        cc.pw.println("I know you are useless ");

        cc.buff.close();
        cc.pw.close();
    }
}

