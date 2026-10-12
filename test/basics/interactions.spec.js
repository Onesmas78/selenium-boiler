// Part 3: interacting with elements and reading their state.
const assert = require('node:assert/strict');
const { By, Key } = require('selenium-webdriver');
const { useBrowser } = require('../support/browser');

describe('Interactions', function () {
  const browser = useBrowser();

  it('logs in with valid credentials', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com/login');

    const username = await driver.findElement(By.id('username'));
    await username.clear();
    await username.sendKeys('tomsmith');
    assert.equal(await username.getProperty('value'), 'tomsmith');

    await driver.findElement(By.id('password')).sendKeys('SuperSecretPassword!', Key.ENTER);

    const message = await driver.findElement(By.id('flash')).getText();
    assert.ok(message.includes('You logged into a secure area!'));
    assert.ok((await driver.getCurrentUrl()).endsWith('/secure'));
  });

  it('toggles checkboxes', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com/checkboxes');
    const [first, second] = await driver.findElements(By.css('#checkboxes input'));

    assert.equal(await first.isSelected(), false); // unchecked by default
    await first.click();
    assert.equal(await first.isSelected(), true);

    assert.equal(await second.isSelected(), true); // checked by default
  });

  it('reads attributes and state', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com/login');
    const loginBtn = await driver.findElement(By.css("button[type='submit']"));

    assert.equal(await loginBtn.getDomAttribute('type'), 'submit');
    assert.equal(await loginBtn.getTagName(), 'button');
    assert.ok(await loginBtn.isDisplayed());
    assert.ok(await loginBtn.isEnabled());
  });
});
