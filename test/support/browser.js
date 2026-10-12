// Opens a browser before each test and closes it after (JavaScript version of BaseTest.java).
// On failure, a screenshot is added to the HTML report.
const addContext = require('mochawesome/addContext');
const { createDriver } = require('../../src/driverFactory');

const WAIT_MS = 10000;

function useBrowser() {
  const browser = {};

  beforeEach(async function () {
    browser.driver = await createDriver();
    await browser.driver.manage().window().maximize();
  });

  afterEach(async function () {
    if (!browser.driver) {
      return;
    }
    if (this.currentTest.state === 'failed') {
      const png = await browser.driver.takeScreenshot();
      addContext(this, { title: 'Screenshot at failure', value: `data:image/png;base64,${png}` });
    }
    await browser.driver.quit();
    browser.driver = undefined;
  });

  return browser;
}

module.exports = { useBrowser, WAIT_MS };
