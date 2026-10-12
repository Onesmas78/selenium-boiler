package com.onesmas.basics;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Part 3: interacting with elements and reading their state. */
public class InteractionsTest extends BaseTest {

    @Test
    void loginWithValidCredentials() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement username = driver.findElement(By.id("username"));
        username.clear();
        username.sendKeys("tomsmith");
        assertEquals("tomsmith", username.getDomProperty("value"));

        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!", Keys.ENTER);

        String message = driver.findElement(By.id("flash")).getText();
        assertTrue(message.contains("You logged into a secure area!"));
        assertTrue(driver.getCurrentUrl().endsWith("/secure"));
    }

    @Test
    void toggleCheckBoxes() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        List<WebElement> boxes = driver.findElements(By.cssSelector("#checkboxes input"));

        WebElement first = boxes.get(0);
        assertFalse(first.isSelected());     // unchecked by default
        first.click();
        assertTrue(first.isSelected());

        WebElement second = boxes.get(1);
        assertTrue(second.isSelected());     // checked by default
    }

    @Test
    void readAttributesAndState() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        assertEquals("submit", loginBtn.getDomAttribute("type"));
        assertEquals("button", loginBtn.getTagName());
        assertTrue(loginBtn.isDisplayed());
        assertTrue(loginBtn.isEnabled());
    }
}
