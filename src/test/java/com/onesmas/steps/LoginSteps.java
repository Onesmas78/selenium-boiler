package com.onesmas.steps;

import com.onesmas.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private WebDriver driver;

    @Before
    void setUp() {
        driver = DriverFactory.create();
        driver.manage().window().maximize();
    }

    @After
    void tearDown(Scenario scenario) {
        if (driver == null) {
            return;
        }
        if (scenario.isFailed()) {
            byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(png, "image/png", "Screenshot at failure");
        }
        driver.quit();
    }

    @Given("the user is on the login page")
    public void openLoginPage() {
        driver.get("https://www.saucedemo.com");
    }

    @When("the user logs in with {string} and {string}")
    public void login(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @Then("the products page is displayed")
    public void productsPageIsDisplayed() {
        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Then("an error containing {string} is displayed")
    public void errorIsDisplayed(String expected) {
        String error = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        assertTrue(error.contains(expected), "Actual error: " + error);
    }
}
