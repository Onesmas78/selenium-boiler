// Part 2: locating elements.
const assert = require('node:assert/strict');
const { By, WebElement, locateWith } = require('selenium-webdriver');
const { useBrowser } = require('../support/browser');

describe('Locators', function () {
  const browser = useBrowser();

  it('locates login page elements', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com/login');

    const heading = await driver.findElement(By.tagName('h2'));
    const username = await driver.findElement(By.id('username'));
    const password = await driver.findElement(By.name('password'));
    const loginBtn = await driver.findElement(By.css("button[type='submit']"));
    const sameBtn = await driver.findElement(By.xpath("//button[contains(.,'Login')]"));

    assert.equal(await heading.getText(), 'Login Page');
    assert.ok((await username.isDisplayed()) && (await password.isDisplayed()));
    assert.ok(await WebElement.equals(loginBtn, sameBtn)); // two locators, same element
  });

  it('uses link text locators', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com/login');

    const exact = await driver.findElement(By.linkText('Elemental Selenium'));
    const partial = await driver.findElement(By.partialLinkText('Elemental'));
    assert.ok(await WebElement.equals(exact, partial));
  });

  it('findElements returns a list, or an empty one', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com/checkboxes');

    const boxes = await driver.findElements(By.css('#checkboxes input'));
    assert.equal(boxes.length, 2);

    // No match: empty list, no error
    assert.equal((await driver.findElements(By.id('does-not-exist'))).length, 0);
  });

  it('uses a relative locator', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com/login');

    const passwordField = await driver.findElement(
      locateWith(By.tagName('input')).below(By.id('username')));
    assert.equal(await passwordField.getDomAttribute('id'), 'password');
  });
});
