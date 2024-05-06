package account;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String accountName;
    private double balance;
    private List<String> transactions;

    public Account(String accountNumber, String accountName)
    {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(String transactionId, double amount)
    {
        balance += amount;
        transactions.add(transactionId);
    }

    public void withdraw(String transactionId, double amount)
    {
        balance -= amount;
        transactions.add(transactionId);
    }

    public String produceStatement()
    {
        StringBuilder statement = new StringBuilder();
        statement.append("Name: ").append(accountName).append("\n");
        statement.append("Account: ").append(accountNumber).append("\n");
        statement.append("Balance: ").append(String.format("%.2f", balance)).append("\n");
        for (String transaction : transactions)
        {
            statement.append(transaction).append("\n");
        }
        return statement.toString();
    }
}
