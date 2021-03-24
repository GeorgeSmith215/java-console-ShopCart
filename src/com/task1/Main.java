package com.task1;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    static Interface anInterface = new Interface();
    public static void main(String[] args) {
        anInterface.welcome();
        String account,password;
        while (true) {
            int option = 1;
            System.out.println("Please Login First Or Register");
            System.out.print("Press  1:Login  2:Register  Else:Exit \nPlease Enter: ");
            Scanner mainScanner = new Scanner(System.in);
            try {
                option = mainScanner.nextInt();
                if (option == 1) {
                    System.out.println("Please Enter Your Account:");
                    account = mainScanner.next();
                    System.out.println("Please Enter Your Password:");
                    password = mainScanner.next();
                    anInterface.login(account,password);
                } else if (option == 2) {
                    System.out.print("Please Enter Your Account:");
                    account = mainScanner.next();
                    System.out.print("Please Enter Your Password:");
                    password = mainScanner.next();
                    anInterface.register(account,password);
                } else {
                    exit(0);
                }
            }catch (Exception exceptionMain){
                exceptionMain.getMessage();
                exit(0);
            }

        }
    }
}