package com.onesmas.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private WebDriver driver;

    @Before
    void setUp() {
        driver = new ChromeDriver();
    }

    @After
    void tearDown() {
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
}
