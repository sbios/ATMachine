package Authorization;

import Models.User;
import com.ATMachine.Menu;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ATMachine.Main.Users;

public class Registration {
    public static void Start(){
        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.println("To go back, write exit.");
        String regex = "[a-zA-Z]+";
        Pattern pattern = Pattern.compile(regex);


        boolean correctInput = false;
        String FirstName = new String();
        while (!correctInput){
            System.out.print("Write your first name: ");
            userInput = scanner.next();
            userInput = userInput.replaceAll(" ","");
            if(userInput.equalsIgnoreCase("exit")){
                Menu.AuthorizationMenu();
            }
            Matcher matcher = pattern.matcher(userInput);
            if(matcher.matches()){
                FirstName = userInput;
                correctInput = true;
            } else {
                System.out.println("First name can contains only letters A-Z");
            }
        }

        correctInput = false;
        String LastName = new String();
        while (!correctInput){
            System.out.print("Write your last name: ");
            userInput = scanner.next();
            userInput = userInput.replaceAll(" ","");
            if(userInput.equalsIgnoreCase("exit")){
                Menu.AuthorizationMenu();
            }
            Matcher matcher = pattern.matcher(userInput);
            if(matcher.matches()){
                LastName = userInput;
                correctInput = true;
            } else {
                System.out.println("Last name can contains only letters A-Z");
            }
        }

        int pinCode = 0;
        System.out.print("Enter the four-digit PIN code: ");
        correctInput = false;
        while (!correctInput) {
            int PinTryOne = scanner.nextInt();
            System.out.print("Enter the pin code again: ");
            int PinCodeTryTwo = scanner.nextInt();
            if (PinTryOne > 999 && PinTryOne < 10000 && PinTryOne == PinCodeTryTwo) {
                pinCode = PinTryOne;
                correctInput = true;
            } else {
                System.out.println("You made a mistake!");
                System.out.print("Come up with a four-digit pincode: ");
            }
        }

        correctInput = false;
        String Login = new String();
        regex = "^[a-zA-Z1-9@!$]*$";
        pattern = Pattern.compile(regex);
        while (!correctInput) {
            System.out.print("Enter the login: ");
            userInput = scanner.next();
            userInput = userInput.replaceAll(" ","");
            if (userInput.equalsIgnoreCase("exit")) {
                Menu.AuthorizationMenu();
            } else if (Users.containsKey(userInput)){
                System.out.println("This login is already occupied!");
            }
            Matcher matcher = pattern.matcher(userInput);
            if(matcher.matches()){
                Login = userInput;
                correctInput = true;
            } else {
                System.out.println("The username can only contain letters (a-zA-Z), numbers (1-9), and the symbols (@, !, $).");
            }
        }
        System.out.println("Account successfully created!");
        Users.put(Login, new User(FirstName, LastName, pinCode, Login));
        Menu.ATMenu(Users.get(Login));
    }
}
