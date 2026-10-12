// Creates the browser for every test (JavaScript version of DriverFactory.java).
//
// BROWSER=chrome|firefox|edge|safari      (default chrome)
// GRID_URL=http://localhost:4444          (omit to run locally)
// GRID_USER / GRID_PASS                   (only for a Grid that needs a login)
const { Builder, Browser } = require('selenium-webdriver');

const BROWSERS = {
  chrome: Browser.CHROME,
  firefox: Browser.FIREFOX,
  edge: Browser.EDGE,
  safari: Browser.SAFARI,
};

function gridAddress(gridUrl) {
  const { GRID_USER, GRID_PASS } = process.env;
  if (!GRID_USER || !GRID_PASS) {
    return gridUrl;
  }
  const url = new URL(gridUrl);
  url.username = GRID_USER;
  url.password = GRID_PASS;
  return url.toString();
}

async function createDriver() {
  const name = (process.env.BROWSER || 'chrome').toLowerCase();
  const browser = BROWSERS[name];
  if (!browser) {
    throw new Error(`Unknown browser: ${name}`);
  }

  const builder = new Builder().forBrowser(browser);
  if (process.env.GRID_URL) {
    builder.usingServer(gridAddress(process.env.GRID_URL));
  }
  return builder.build();
}

module.exports = { createDriver };
