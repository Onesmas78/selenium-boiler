package com.onesmas.basics;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Part 4: waits and synchronisation. */
public class WaitsTest extends BaseTest {

    /** Fails on purpose: the text appears ~5 s after Start. Remove @Disabled to demo it. */
    @Disabled("Demo of a failing test without a wait")
    @Test
    void failsWithoutWait() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.cssSelector("#start button")).click();

        assertEquals("Hello World!", driver.findElement(By.id("finish")).getText());
    }

    @Test
    void waitsForDynamicContent() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.cssSelector("#start button")).click();

        WebElement finish = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

        assertEquals("Hello World!", finish.getText());
    }

    @Test
    void waitsForSpinnerToDisappear() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.cssSelector("#start button")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        assertEquals("Hello World!", driver.findElement(By.id("finish")).getText());
    }

    @Test
    void fluentWaitExample() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.cssSelector("#start button")).click();

        Wait<WebDriver> fluent = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class);

        WebElement result = fluent.until(d -> d.findElement(By.id("finish")));
        assertEquals("Hello World!", result.getText());
    }
}
