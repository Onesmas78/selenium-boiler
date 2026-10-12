// Part 4: waits and synchronisation.
const assert = require('node:assert/strict');
const { By, until, error } = require('selenium-webdriver');
const { useBrowser, WAIT_MS } = require('../support/browser');

describe('Waits', function () {
  const browser = useBrowser();

  // Fails on purpose: the text appears ~5 s after Start. Change it.skip to it to demo.
  it.skip('fails without a wait', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com/dynamic_loading/2');
    await driver.findElement(By.css('#start button')).click();

    assert.equal(await driver.findElement(By.id('finish')).getText(), 'Hello World!');
  });

  it('waits for dynamic content', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com/dynamic_loading/2');
    await driver.findElement(By.css('#start button')).click();

    const finish = await driver.wait(until.elementLocated(By.id('finish')), WAIT_MS);
    await driver.wait(until.elementIsVisible(finish), WAIT_MS);
    assert.equal(await finish.getText(), 'Hello World!');
  });

  it('waits for the spinner to disappear', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com/dynamic_loading/1');
    await driver.findElement(By.css('#start button')).click();

    const loading = await driver.findElement(By.id('loading'));
    await driver.wait(until.elementIsNotVisible(loading), WAIT_MS);
    assert.equal(await driver.findElement(By.id('finish')).getText(), 'Hello World!');
  });

  it('uses a custom wait condition with polling', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com/dynamic_loading/2');
    await driver.findElement(By.css('#start button')).click();

    // Like Java's FluentWait: 15 s timeout, check every 250 ms, ignore "not found"
    const result = await driver.wait(async (d) => {
      try {
        return await d.findElement(By.id('finish'));
      } catch (e) {
        if (e instanceof error.NoSuchElementError) return false;
        throw e;
      }
    }, 15000, 'finish never appeared', 250);

    assert.equal(await result.getText(), 'Hello World!');
  });
});
