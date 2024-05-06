package glue;

import account.Account;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class AccountSteps {

    Account account = null;

    @Given("^Account exists for Acc No\\. \"([^\"]*)\" with Name \"([^\"]*)\"$")
    public void accountExistsForAccNoWithName(String number, String name)
    {
        account = new Account(number, name);
    }

    @Given("^deposits are made$")
    public void depositsAreMade(List<List<String>> deposits)
    {
        for (List<String> deposit : deposits)
        {
            String transactionId = deposit.get(0);
            double amount = Double.parseDouble(deposit.get(1));
            account.deposit(transactionId, amount);
        }
    }

    @Given("^withdrawls are made$")
    public void withdrawalsAreMade(List<List<String>> withdrawals)
    {
        for (List<String> withdrawal : withdrawals)
        {
            String transactionId = withdrawal.get(0);
            double amount = Double.parseDouble(withdrawal.get(1));
            account.withdraw(transactionId, amount);
        }
    }

    @When("^statement is produced$")
    public void statementIsProduced()
    {
        String statement = account.produceStatement();
        System.out.println("Generated Statement:\n" + statement);
        Assert.assertTrue(statement.contains("Name: Bob Smith"));
        Assert.assertTrue(statement.contains("Account: 12345678"));

    }

    @Then("^statement includes \"([^\"]*)\"$")
    public void statementIncludes(String expectedContent)
    {
        Assert.assertTrue(account.produceStatement().contains(expectedContent));
    }
}
