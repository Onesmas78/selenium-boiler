// Part 1: browser commands and navigation.
const assert = require('node:assert/strict');
const { useBrowser } = require('../support/browser');

describe('Navigation', function () {
  const browser = useBrowser();

  it('navigates between pages', async function () {
    const { driver } = browser;
    await driver.get('https://the-internet.herokuapp.com');
    assert.equal(await driver.getTitle(), 'The Internet');

    await driver.navigate().to('https://the-internet.herokuapp.com/login');
    assert.ok((await driver.getCurrentUrl()).endsWith('/login'));

    await driver.navigate().back();
    assert.equal(await driver.getCurrentUrl(), 'https://the-internet.herokuapp.com/');

    await driver.navigate().forward();
    await driver.navigate().refresh();
    assert.ok((await driver.getCurrentUrl()).endsWith('/login'));
  });

  it('sets a fixed window size', async function () {
    const { driver } = browser;
    await driver.manage().window().setRect({ width: 1280, height: 800 });
    await driver.get('https://the-internet.herokuapp.com');

    const { width, height } = await driver.manage().window().getRect();
    assert.equal(width, 1280);
    assert.equal(height, 800);
  });
});
