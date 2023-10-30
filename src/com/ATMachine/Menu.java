package com.ATMachine;

import Authorization.Login;
import Authorization.Registration;
import Models.User;
import Tools.CashIterations;

import java.util.Scanner;


public class Menu {
    public static void AuthorizationMenu(){
        System.out.println("Welcome to the ATM simulation!" + "\n" +
                "1: Register a new user" + "\n" +
                "2: Login" + "\n" +
                "3: Exit");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                Registration.Start();
                break;

            case 2:
                Login.Start();
                break;

            case 3:
                System.exit(0);
                break;
        }
    }

    public static void ATMenu(User user){
        System.out.println("Welcome back " + user.GetFirstName() + "!");
        Scanner scanner = new Scanner(System.in);
        String userInput = new String();
        while (true) {
            System.out.println("1: Back to authorization" + "\n" +
                    "2: Check balance" + "\n" +
                    "3: Deposit money" + "\n" +
                    "4: Withdraw money" + "\n" +
                    "5: Transfer money" + "\n" +
                    "6: Watch operations history");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    AuthorizationMenu();
                    break;
                case 2:
                    System.out.println("Your balance: " + user.GetBalance());
                    break;
                case 3:
                    Main.atm.DepositMoney(user, CashIterations.ReadAndReturnCash());
                    break;
                case 4:
                    System.out.print("Enter the withdrawal amount: ");
                    Main.atm.WithdrawMoney(user,scanner.nextInt());
                    break;
                case 5:
                    System.out.print("Write login of user to who transfer moneys: ");
                    userInput = scanner.next();
                    String loginForTransfer = new String();
                    if(!Main.Users.containsKey(userInput)){
                        System.out.println("User with same login not founded!");
                    } else {
                        loginForTransfer  = userInput;
                        System.out.print("Write sum what you want to transfer: ");
                        int sum = scanner.nextInt();
                        if(user.GetBalance() >= sum){
                            Main.atm.TransferMoney(user,Main.Users.get(loginForTransfer),sum);
                            System.out.println("You successfully transfer " + sum);
                        } else{
                            System.out.println("You don`t have money enough to do this!");
                        }
                    }
                    break;
                case 6:
                    System.out.print(user.GetOperations());
                    break;
            }
        }
    }
}
