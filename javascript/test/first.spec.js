// First test from the setup class: opens Chrome directly, no shared setup.
const assert = require('node:assert/strict');
const { Builder, Browser } = require('selenium-webdriver');

describe('First test', function () {
  it('home page has expected title', async function () {
    const driver = await new Builder().forBrowser(Browser.CHROME).build();
    try {
      await driver.manage().window().maximize();
      await driver.get('https://www.saucedemo.com');
      assert.equal(await driver.getTitle(), 'Swag Labs');
    } finally {
      await driver.quit();
    }
  });
});
