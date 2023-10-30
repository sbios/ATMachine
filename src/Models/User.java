package Models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class User {
    private String FirstName;
    private String LastName;
    private int PinCode;
    private String Login;
    private int Balance;
    private List<UserOperation> userOperations;

    public User(String FirstName, String LastName, int PinCode, String Login) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.PinCode = PinCode;
        this.Login = Login;
        userOperations = new LinkedList<>();
    }

    public void NewOperation(Date date, String type, int sum){
        userOperations.add(new UserOperation(date,type,sum));
    }

    public String GetOperations(){
        StringBuilder result = new StringBuilder();
        for (UserOperation userOperation : userOperations){
            result.append(userOperation.toString());
            result.append("\n");
        }
        return result.toString();
    }

    public void ReduceBalance(int sum){
        if(sum>0){
            this.Balance -= sum;
        }
    }

    public void IncreaseBalance(int sum){
        if(sum>0){
            this.Balance += sum;
        }
    }

    public double GetBalance(){
        return this.Balance;
    }

    public String GetFirstName(){
        return this.FirstName;
    }

    public String GetLastName(){
        return this.LastName;
    }

    public String GetLogin(){
        return this.Login;
    }

    public int GetPinCode(){
        return this.PinCode;
    }

    @Override
    public String toString() {
        return "Models.User{" + "FirstName='" + FirstName + '\'' + ", LastName='" + LastName + '\'' + "}";
    }
}
