// Opens a browser for each scenario, screenshots on failure, then closes it.
const { Before, After, Status, setDefaultTimeout } = require('@cucumber/cucumber');
const { createDriver } = require('../../src/driverFactory');

// Starting a browser takes longer than Cucumber's default 5 s step limit
setDefaultTimeout(60 * 1000);

Before(async function () {
  this.driver = await createDriver();
  await this.driver.manage().window().maximize();
});

After(async function (scenario) {
  if (!this.driver) {
    return;
  }
  if (scenario.result?.status === Status.FAILED) {
    const png = await this.driver.takeScreenshot();
    this.attach(Buffer.from(png, 'base64'), 'image/png');
  }
  await this.driver.quit();
});
