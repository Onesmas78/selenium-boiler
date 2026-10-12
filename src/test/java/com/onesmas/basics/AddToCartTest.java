package com.onesmas.basics;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddToCartTest extends BaseTest {

    @Test
    void standardUserCanAddProductToCart() {
        driver.get("https://www.saucedemo.com");

        driver.findElement(By.cssSelector("[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();

        wait.until(ExpectedConditions.and(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("inventory_item")),
                ExpectedConditions.urlContains("inventory.html")));

        List<WebElement> products = driver.findElements(By.className("inventory_item"));
        assertEquals(6, products.size());

        WebElement firstProduct = products.getFirst();
        String name =
                firstProduct.findElement(By.className("inventory_item_name")).getText();
        firstProduct.findElement(By.tagName("button")).click();

        WebElement badge =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));

        assertEquals("1", badge.getText());

        System.out.println("Added: " + name);
    }
}
