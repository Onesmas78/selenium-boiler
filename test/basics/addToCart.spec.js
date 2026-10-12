// Worked example: browser, locators, interactions and waits together.
const assert = require('node:assert/strict');
const { By, until } = require('selenium-webdriver');
const { useBrowser, WAIT_MS } = require('../support/browser');

describe('Add to cart', function () {
  const browser = useBrowser();

  it('standard user can add a product to the cart', async function () {
    const { driver } = browser;
    await driver.get('https://www.saucedemo.com');

    await driver.findElement(By.css("[data-test='username']")).sendKeys('standard_user');
    await driver.findElement(By.css("[data-test='password']")).sendKeys('secret_sauce');
    await driver.findElement(By.css("[data-test='login-button']")).click();

    await driver.wait(until.urlContains('inventory.html'), WAIT_MS);
    await driver.wait(until.elementsLocated(By.className('inventory_item')), WAIT_MS);

    const products = await driver.findElements(By.className('inventory_item'));
    assert.equal(products.length, 6);

    const firstProduct = products[0];
    const name = await firstProduct.findElement(By.className('inventory_item_name')).getText();
    await firstProduct.findElement(By.tagName('button')).click();

    const badge = await driver.wait(until.elementLocated(By.className('shopping_cart_badge')), WAIT_MS);
    await driver.wait(until.elementIsVisible(badge), WAIT_MS);
    assert.equal(await badge.getText(), '1');
    console.log(`Added: ${name}`);
  });
});
