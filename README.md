# selenium-boiler: JavaScript

The course tests from `main`, written in JavaScript with Node.js. This branch holds only the JavaScript version; the Java version is on `main`. Each Java class has a matching file here, so you can compare them side by side.

| Java (`main`) | JavaScript (this branch) |
| --- | --- |
| JUnit | Mocha |
| Cucumber | Cucumber.js (same `login.feature`) |
| `BaseTest.java` | `useBrowser()` in `test/support/browser.js` |
| `DriverFactory.java` | `src/driverFactory.js` |
| Cucumber HTML report | Mochawesome report + Cucumber HTML report |

## 1. Install

- Node.js 22.12 or newer: `node -v`
- Google Chrome

## 2. Set up

```bash
git clone --branch javascript https://github.com/Onesmas78/selenium-boiler.git selenium-javascript
cd selenium-javascript
npm install
```

## 3. What's inside

```
selenium-javascript/
├── package.json
├── .mocharc.json                 Mocha settings (timeout, report)
├── cucumber.js                   Cucumber settings (paths, report)
├── src/driverFactory.js          Creates the browser (local or Grid)
├── test/
│   ├── support/browser.js        Opens/closes the browser, screenshot on failure
│   ├── first.spec.js             First test from the setup class
│   └── basics/
│       ├── navigation.spec.js
│       ├── locators.spec.js
│       ├── interactions.spec.js
│       ├── waits.spec.js
│       ├── addToCart.spec.js
│       ├── exercises.spec.js
│       └── exerciseSolutions.spec.js
└── features/
    ├── login.feature
    ├── step_definitions/login.steps.js
    └── support/hooks.js          Browser per scenario, screenshot on failure
```

Every Selenium call returns a Promise, so each one needs `await`:

```js
const { driver } = browser;
await driver.get('https://the-internet.herokuapp.com');
assert.equal(await driver.getTitle(), 'The Internet');
```

A missing `await` is the most common bug: the test moves on before the browser has finished.

## 4. Run the tests

Run these from the project folder:

```bash
npm test                                      # all Mocha tests
npx mocha test/basics/navigation.spec.js      # one file
npx mocha --grep checkbox                     # tests whose name contains "checkbox"
npm run test:bdd                              # feature-file scenarios
```

Reports:
- Mocha: `reports/report.html`
- Cucumber: `reports/cucumber.html`

Failed tests include a screenshot.

## 5. Choose a browser or use a Grid

Settings come from environment variables:

```bash
BROWSER=firefox npm test                                  # chrome (default), firefox, edge, safari
GRID_URL=http://localhost:4444 BROWSER=firefox npm test   # run on a Grid
```

For the class Grid, also set the login from your instructor:

```bash
export GRID_USER=student
export GRID_PASS='<password from class>'
GRID_URL=http://<grid-ip>:4444 npm test
```

On Windows PowerShell, set each variable first (`$env:BROWSER="firefox"`), then run `npm test`.

See the README on the `main` branch for how to start a Grid on your own machine.

## 6. Exercises

1. Open `test/basics/exercises.spec.js`.
2. Change `describe.skip(` to `describe(`.
3. Replace each `assert.fail('TODO')` with your code.
4. Run `npx mocha test/basics/exercises.spec.js`, then compare with `exerciseSolutions.spec.js`.

## Java to JavaScript cheat sheet

| Java | JavaScript |
| --- | --- |
| `driver.findElement(By.id("x"))` | `await driver.findElement(By.id('x'))` |
| `By.cssSelector("...")` | `By.css('...')` |
| `el.sendKeys("a")` / `el.getText()` | `await el.sendKeys('a')` / `await el.getText()` |
| `driver.navigate().back()` | `await driver.navigate().back()` |
| `el.getDomProperty("value")` | `await el.getProperty('value')` |
| `wait.until(ExpectedConditions.visibilityOfElementLocated(by))` | `await driver.wait(until.elementIsVisible(el), 10000)` |
| `assertEquals(a, b)` | `assert.equal(a, b)` |
| `assertEquals(el1, el2)` | `assert.ok(await WebElement.equals(el1, el2))` |
| `@Disabled` | `it.skip(...)` / `describe.skip(...)` |

## Troubleshooting

| Problem | Fix |
| --- | --- |
| `Cannot find module 'selenium-webdriver'` | Run `npm install` in the project folder |
| `Unsupported engine` or syntax errors on install | Update Node.js to 22.12 or newer |
| `Timeout of 60000ms exceeded` | The page or browser is slow; check your internet and the locator |
| `function timed out` in Cucumber | Same as above |
| `NoSuchElementError` | Check the locator in Chrome DevTools; add a wait |
