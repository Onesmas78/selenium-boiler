// Answers for exercises.spec.js.
const assert = require('node:assert/strict');
const { By, WebElement, until } = require('selenium-webdriver');
const { useBrowser, WAIT_MS } = require('../support/browser');

describe('Exercise solutions', function () {
  const browser = useBrowser();

  it('ex1 navigation', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com');
    await driver.findElement(By.linkText('Form Authentication')).click();
    assert.ok((await driver.getCurrentUrl()).endsWith('/login'));
    await driver.navigate().back();
    assert.equal(await driver.getTitle(), 'The Internet');
  });

  it('ex2 three locators for password', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com/login');
    const byId = await driver.findElement(By.id('password'));
    const byCss = await driver.findElement(By.css("input[type='password']"));
    const byXpath = await driver.findElement(By.xpath("//input[@name='password']"));
    assert.ok(await WebElement.equals(byId, byCss));
    assert.ok(await WebElement.equals(byId, byXpath));
  });

  it('ex3 invalid password', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com/login');
    await driver.findElement(By.id('username')).sendKeys('tomsmith');
    await driver.findElement(By.id('password')).sendKeys('wrongPassword');
    await driver.findElement(By.css("button[type='submit']")).click();
    const message = await driver.findElement(By.id('flash')).getText();
    assert.ok(message.includes('Your password is invalid!'));
  });

  it('ex4 add and remove elements', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com/add_remove_elements/');
    const add = await driver.findElement(By.xpath("//button[text()='Add Element']"));
    for (let i = 0; i < 3; i++) {
      await add.click();
    }
    const deletes = await driver.findElements(By.xpath("//button[text()='Delete']"));
    assert.equal(deletes.length, 3);

    await deletes[0].click();
    // Find again: the old list may hold stale elements
    assert.equal((await driver.findElements(By.xpath("//button[text()='Delete']"))).length, 2);
  });

  it('ex5 wait for hidden element', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com/dynamic_loading/1');
    await driver.findElement(By.css('#start button')).click();
    const finish = await driver.findElement(By.id('finish'));
    await driver.wait(until.elementIsVisible(finish), WAIT_MS);
    assert.equal(await finish.getText(), 'Hello World!');
  });

  it('ex6 locked out user', async function () {
    const { driver } = browser;
    await driver.get('https://www.saucedemo.com');
    await driver.findElement(By.id('user-name')).sendKeys('locked_out_user');
    await driver.findElement(By.id('password')).sendKeys('secret_sauce');
    await driver.findElement(By.id('login-button')).click();
    const errorText = await driver.findElement(By.css("[data-test='error']")).getText();
    assert.ok(errorText.includes('locked out'));
  });
});
