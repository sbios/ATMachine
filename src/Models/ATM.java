package Models;

import java.util.*;

public class ATM {
    private List<ATMOperation> Operations;
    private Map<Integer,Integer> Banknotes;
    private int TotalBalance;

    public ATM(){
        Operations = new LinkedList<>();
        this.TotalBalance = 0;
        this.Banknotes = new LinkedHashMap<>();
        for(int banknote : Resources.Banknotes.BanknotesList){
            Banknotes.put(banknote,0);
        }
    }

    public boolean CanGiveCash(int sum){
        for(Map.Entry<Integer,Integer> banknote: this.Banknotes.entrySet()){
            if(sum >= banknote.getKey()){
                if(banknote.getValue() <  Math.floorDiv(sum,banknote.getKey())){
                    sum -= banknote.getKey() * banknote.getValue();
                } else {
                    sum -= banknote.getKey() * Math.floorDiv(sum,banknote.getKey());
                }
            }
        }
        return sum == 0;
    }

    private HashMap<Integer,Integer> GiveCash(int sum){
        HashMap<Integer,Integer> banknotesToWithdraw = new HashMap<>();
        for(Map.Entry<Integer,Integer> banknote: this.Banknotes.entrySet()){
            if(sum >= banknote.getKey()){
                if(banknote.getValue() <  Math.floorDiv(sum,banknote.getKey())){
                    this.Banknotes.put(banknote.getKey(), 0);
                    this.TotalBalance -= banknote.getKey() * banknote.getValue();
                    banknotesToWithdraw.put(banknote.getKey(),banknote.getValue());
                    sum -= banknote.getKey() * banknote.getValue();
                } else {
                    this.Banknotes.put(banknote.getKey(),banknote.getValue() - Math.floorDiv(sum,banknote.getKey()));
                    this.TotalBalance -= banknote.getKey() * Math.floorDiv(sum,banknote.getKey());
                    banknotesToWithdraw.put(banknote.getKey(),Math.floorDiv(sum,banknote.getKey()));
                    sum -= banknote.getKey() * Math.floorDiv(sum,banknote.getKey());
                }
            }
        }
        return banknotesToWithdraw;
    }
    public void WithdrawMoney(User user,int sum){
        if(user.GetBalance() >= sum && this.TotalBalance >= sum && CanGiveCash(sum)){
            user.ReduceBalance(sum);
            GiveCash(sum);
            Date date = new Date();
            NewOperation(user,date,"Withdraw",sum);
            user.NewOperation(date,"Withdraw",sum);
        }
    }

    public void DepositMoney(User user, HashMap<Integer,Integer> banknotes){
        if(!banknotes.isEmpty()){
            int sum = 0;
            for(Map.Entry<Integer,Integer> banknote : banknotes.entrySet()){
                sum += banknote.getKey() * banknote.getValue();
                this.Banknotes.put(banknote.getKey(),banknote.getValue());
                this.TotalBalance += banknote.getKey()*banknote.getValue();
                user.IncreaseBalance(banknote.getKey()*banknote.getValue());
            }
            Date date = new Date();
            NewOperation(user,date,"Deposit",sum);
            user.NewOperation(date,"Deposit",sum);
        }
    }

    public void TransferMoney(User sender, User accepter, int sum){
        sender.ReduceBalance(sum);
        accepter.IncreaseBalance(sum);
        Date date = new Date();
        NewOperation(sender,date,"Sending money to " + accepter.GetLogin(),sum);
        NewOperation(accepter,date,"Receiving money from " + sender.GetLogin(),sum);
        sender.NewOperation(date,"Sending money",sum);
        accepter.NewOperation(date,"Receiving money",sum);
    }

    public void NewOperation(User user, Date date, String type, int sum){
        Operations.add(new ATMOperation(user,date,type,sum));
    }

    public String GetOperations(){
        StringBuilder listOfOperations = new StringBuilder();
        for(ATMOperation operatiom : Operations){
            listOfOperations.append(operatiom.toString());
            listOfOperations.append("\n");
        }
        return  listOfOperations.toString();
    }

    @Override
    public String toString() {
        return "Amount of bills{"+ this.TotalBalance + "}\n" + "Banknotes in ATM{" + this.Banknotes.toString() + "}\n";
    }
}
