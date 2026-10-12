// Step definitions for login.feature (JavaScript version of LoginSteps.java).
const assert = require('node:assert/strict');
const { Given, When, Then } = require('@cucumber/cucumber');
const { By } = require('selenium-webdriver');

Given('the user is on the login page', async function () {
  await this.driver.get('https://www.saucedemo.com');
});

When('the user logs in with {string} and {string}', async function (username, password) {
  await this.driver.findElement(By.id('user-name')).sendKeys(username);
  await this.driver.findElement(By.id('password')).sendKeys(password);
  await this.driver.findElement(By.id('login-button')).click();
});

Then('the products page is displayed', async function () {
  assert.ok((await this.driver.getCurrentUrl()).includes('inventory.html'));
});

Then('an error containing {string} is displayed', async function (expected) {
  const error = await this.driver.findElement(By.css("[data-test='error']")).getText();
  assert.ok(error.includes(expected), `Actual error: ${error}`);
});
