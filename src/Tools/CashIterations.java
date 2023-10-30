package Tools;

import Resources.Banknotes;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CashIterations {
    public static HashMap<Integer,Integer> ReadAndReturnCash(){
        HashMap<Integer,Integer> cash = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        for (int banknote : Banknotes.BanknotesList) {
            System.out.print("what is the number of banknotes of face value " + banknote + " you want to put in: ");
            cash.put(banknote,scanner.nextInt());
        }
        return cash;
    }
}
