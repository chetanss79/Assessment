package glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class GoogleSteps {

    @Given("url {string} is launched")
    public void url(String url)
    {
        W.get().driver.get(url);
        acceptCookiesIfWarned();
    }

    private static void acceptCookiesIfWarned() {
        try
        {
            W.get().driver.findElement(By.cssSelector("#L2AGLb")).click();
        }
        catch (NoSuchElementException ignored)
        {
        }
    }

    @When("About page is shown")
    public void aboutPageIsShown()
    {
        W.get().driver.findElement(By.linkText("About")).click();
    }

    @Then("page displays {string}")
    public void pageDisplays(String expectedText)
    {
        //String bodyText = W.get().driver.findElement(By.tagName("body")).getText();
        String bodyText = W.get().driver.findElement(By.xpath("//h1[@class='modules-lib__mission-statement__headline glue-headline glue-headline--fluid-2']")).getText();
        //Assert.assertTrue("Text not found on page", bodyText.contains(expectedText));
        Assert.assertEquals(expectedText,bodyText);
        System.out.println("Expected Result: " + expectedText);
        System.out.println("Actual Result:   " + bodyText);
    }

    @When("searching for {string}")
    public void searchingFor(String query)
    {
        W.get().driver.findElement(By.name("q")).sendKeys(query);
        W.get().driver.findElement(By.name("btnK")).click();
    }

    @Then("results contain {string}")
    public void resultsContain(String expectedText)
    {
        String resultsText = W.get().driver.findElement(By.id("search")).getText();
        Assert.assertTrue("Expected text not found in search results", resultsText.contains(expectedText));
    }

    @Then("result stats are displayed")
    public void resultStatsAreDisplayed()
    {
        Assert.assertTrue(W.get().driver.findElement(By.id("result-stats")).isDisplayed());
    }

    @Then("number of {string} is more than {int}")
    public void numberOfIsMoreThan(String type, int number)
    {
        String resultsStatsText = W.get().driver.findElement(By.id("result-stats")).getText();
        if ("results".equals(type)) {
            // Assuming the number of results is always an integer
            String resultsNumberStr = resultsStatsText.split(" ")[1].replaceAll("[^\\d]", "");
            long resultsNumber = Long.parseLong(resultsNumberStr);
            Assert.assertTrue("Number of results is not more than " + number, resultsNumber > number);
        } else if ("seconds".equals(type)) {
            // Assuming the time is always in seconds and may be a decimal
            String secondsStr = resultsStatsText.split(" ")[3].replaceAll("[^\\d.]", "");
            double seconds = Double.parseDouble(secondsStr);
            Assert.assertTrue("Number of seconds is not more than " + number, seconds > number);
        }
    }


}
