package com.onesmas.basics;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InteractionsTest extends BaseTest{

    @Test
    void toggleCheckBoxes(){
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
