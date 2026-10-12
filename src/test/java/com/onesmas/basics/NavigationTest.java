package com.onesmas.basics;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationTest extends BaseTest{
    @Test
    void navigateBetweenPages(){
        driver.get("https://the-internet.herokuapp.com");
        assertEquals("The Internet",driver.getTitle());

        driver.navigate().to("https://the-internet.herokuapp.com/login");
        assertTrue(driver.getCurrentUrl().endsWith("/login"));

        driver.navigate().back();
        assertEquals("https://the-internet.herokuapp.com",driver.getCurrentUrl());

        driver.navigate().forward();
        driver.navigate().refresh();
        assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    void setFixedWindowSize(){
        driver.manage().window().setSize(new Dimension(1280,800));
        driver.get("https://the-internet.herokuapp.com");

        Dimension size = driver.manage().window().getSize();
        assertEquals(1280, size.getWidth());
        assertEquals(800, size.getHeight());
    }
}
