package com.onesmas.basics;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

/** Part 2: locating elements. */
public class LocatorsTest extends BaseTest {

    @Test
    void locateLoginPageElements() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement heading  = driver.findElement(By.tagName("h2"));
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        WebElement sameBtn  = driver.findElement(By.xpath("//button[contains(.,'Login')]"));

        assertEquals("Login Page", heading.getText());
        assertTrue(username.isDisplayed() && password.isDisplayed());
        assertEquals(loginBtn, sameBtn);   // two locators, same element
    }

    @Test
    void linkTextLocators() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement exact   = driver.findElement(By.linkText("Elemental Selenium"));
        WebElement partial = driver.findElement(By.partialLinkText("Elemental"));
        assertEquals(exact, partial);
    }

    @Test
    void findElementsReturnsListOrEmpty() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        List<WebElement> boxes = driver.findElements(By.cssSelector("#checkboxes input"));
        assertEquals(2, boxes.size());

        // No match: empty list, no exception
        assertTrue(driver.findElements(By.id("does-not-exist")).isEmpty());
    }

    @Test
    void relativeLocator() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement passwordField = driver.findElement(
                with(By.tagName("input")).below(By.id("username")));
        assertEquals("password", passwordField.getDomAttribute("id"));
    }
}
