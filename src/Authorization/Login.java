package Authorization;

import Models.User;
import com.ATMachine.Menu;

import java.util.Scanner;

import static com.ATMachine.Main.Users;

public class Login {
    public static void Start(){
        Scanner scanner = new Scanner(System.in);
        String userInput;

        System.out.println("to go back, write exit.");
        System.out.print("Enter your login: ");
        userInput = scanner.next();
        if(userInput.equalsIgnoreCase("exit")){
            Menu.AuthorizationMenu();
        }
        String Login = userInput;
        if(!Users.containsKey(Login)){
            int loginChoice = 0;
            while (!(Users.containsKey(Login))) {
                System.out.print("there is no such user!");
                System.out.print("write exit to go back or write login again");
                userInput = scanner.next();
                if(userInput.equalsIgnoreCase("exit")){
                    Menu.AuthorizationMenu();
                }
                Login = userInput;
            }
        } else {
            System.out.print("Enter the PIN code: ");
            userInput = scanner.next();
            if(userInput.equalsIgnoreCase("exit")){
                Menu.AuthorizationMenu();
            }
            int Password = 0;
            try {
               Password = Integer.parseInt(userInput);
            } catch (Exception e){
                System.out.println("the PIN code can contain only numbers from 1 to 9!");
            }

            User user = Users.get(Login);
            if(user.GetPinCode() != Password){
                int attempts = 4;
                int passwordChoice = 0;
                while (attempts >0 || !(user.GetPinCode() == Password)){
                    attempts--;
                    System.out.print("you entered the wrong PIN code!");
                    System.out.print("you have 3 attempts left");
                    System.out.print("write exit to go back or write password again");
                    userInput = scanner.next();
                    if(userInput.equalsIgnoreCase("exit")){
                        Menu.AuthorizationMenu();
                    }
                    try {
                        Password = Integer.parseInt(userInput);
                    } catch (Exception e){
                        System.out.println("the PIN code can contain only numbers from 1 to 9!");
                    }
                }
            }

            if(user.GetPinCode() == Password){
                Menu.ATMenu(user);
            }
        }
    }
}
