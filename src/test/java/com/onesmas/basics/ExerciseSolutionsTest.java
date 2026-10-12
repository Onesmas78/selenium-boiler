package com.onesmas.basics;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Answers for ExercisesTest. */
public class ExerciseSolutionsTest extends BaseTest {

    @Test
    void ex1_navigation() {
        driver.get("https://the-internet.herokuapp.com");
        driver.findElement(By.linkText("Form Authentication")).click();
        assertTrue(driver.getCurrentUrl().endsWith("/login"));
        driver.navigate().back();
        assertEquals("The Internet", driver.getTitle());
    }

    @Test
    void ex2_threeLocatorsForPassword() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement byId    = driver.findElement(By.id("password"));
        WebElement byCss   = driver.findElement(By.cssSelector("input[type='password']"));
        WebElement byXpath = driver.findElement(By.xpath("//input[@name='password']"));
        assertEquals(byId, byCss);
        assertEquals(byId, byXpath);
    }

    @Test
    void ex3_invalidPassword() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("wrongPassword");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        assertTrue(driver.findElement(By.id("flash")).getText().contains("Your password is invalid!"));
    }

    @Test
    void ex4_addRemoveElements() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        WebElement add = driver.findElement(By.xpath("//button[text()='Add Element']"));
        for (int i = 0; i < 3; i++) {
            add.click();
        }
        List<WebElement> deletes = driver.findElements(By.xpath("//button[text()='Delete']"));
        assertEquals(3, deletes.size());

        deletes.get(0).click();
        // Find again: the old list may hold stale elements
        assertEquals(2, driver.findElements(By.xpath("//button[text()='Delete']")).size());
    }

    @Test
    void ex5_waitForHiddenElement() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.cssSelector("#start button")).click();
        WebElement finish = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        assertEquals("Hello World!", finish.getText());
    }

    @Test
    void ex6_lockedOutUser() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String error = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        assertTrue(error.contains("locked out"));
    }
}
