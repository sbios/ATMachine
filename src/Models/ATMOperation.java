package Models;

import java.util.Date;

public class ATMOperation extends UserOperation {
    private User user;
    public ATMOperation(User user, Date date, String type, int sum) {
        super(date, type, sum);
        this.user = user;

    }

    @Override
    public String toString() {
        return user.toString() + "\n" + super.toString();
    }
}
