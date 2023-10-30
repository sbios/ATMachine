package Models;

import java.util.Date;

public class UserOperation {
    private Date Date;
    private String Type;
    private int Sum;

    public UserOperation(Date date, String type, int sum){
        this.Date = date;
        this.Type = type;
        this.Sum = sum;
    }

    @Override
    public String toString() {
        return "Date{" + this.Date.toString() + "}\n" + "Type{" + this.Type + "}\n" + "Amount{" + this.Sum + "}\n";
    }
}
