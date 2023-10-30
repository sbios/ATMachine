package com.ATMachine;

import Models.ATM;
import Models.User;
import java.util.HashMap;

public class Main {
    public static ATM atm = new ATM();
    public static HashMap<String, User> Users = new HashMap<>();

    public static void main(String[] args) {
        Menu.AuthorizationMenu();
    }
}