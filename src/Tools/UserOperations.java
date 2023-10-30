package Tools;

import Models.User;
import Models.UserOperation;

import java.util.Date;

public class UserOperations {
    public static UserOperation depositOperation(User user,int sum){
        return new UserOperation(new Date(),"Deposit",sum);
    }

    public static UserOperation withdrawOperation(User user,int sum){
        return new UserOperation(new Date(),"Withdraw",sum);
    }

    public static UserOperation sendOperation(User sender, User receiver, int sum){
        return new UserOperation(new Date(),"Sendindg money to " + receiver.GetFirstName(),sum);
    }

    public static UserOperation receiveOperation(User sender, User receiver, int sum){
        return new UserOperation(new Date(),"Receiving money from " + sender.GetFirstName(),sum);
    }
}
