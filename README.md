# selenium-boiler

Course project for Selenium WebDriver with Java, JUnit and Cucumber. Pull it before each class to get the latest tests.

## Branches

| Branch | Contents |
| --- | --- |
| `main` | Java (this README) |
| `python` | Same tests in Python: see `python/README.md` on that branch |
| `javascript` | Same tests in JavaScript: see `javascript/README.md` on that branch |

## 1. Install

| Tool | Version | Check with |
| --- | --- | --- |
| JDK | 21 | `java -version` |
| Maven | 3.9 or newer | `mvn -v` (must show Java 21) |
| Git | any | `git --version` |
| Google Chrome | latest | |
| IntelliJ IDEA | latest, with the **Cucumber for Java** plugin | |

You don't need to download chromedriver. Selenium downloads the right driver on the first run.

## 2. Get the project

```bash
git clone https://github.com/Onesmas78/selenium-boiler.git
cd selenium-boiler
mvn test-compile
```

In IntelliJ: **File → Open**, pick the `selenium-boiler` folder, and set the project SDK to 21.

Get updates before each class:

```bash
git pull
```

If `git pull` complains about your local changes, commit them first (`git add . && git commit -m "my work"`) and pull again.

## 3. What's inside

```
src/test/java/com/onesmas/
├── DriverFactory.java        Creates the browser (local or Grid)
├── FirstTest.java            First test from the setup class
├── RunCucumber.java          Runs the .feature files
├── steps/LoginSteps.java     Cucumber step definitions
└── basics/
    ├── BaseTest.java         Opens and closes the browser for every test
    ├── NavigationTest.java   Browser commands
    ├── LocatorsTest.java     Locators
    ├── InteractionsTest.java Clicking, typing, reading state
    ├── WaitsTest.java        Explicit and fluent waits
    ├── AddToCartTest.java    Everything together
    ├── ExercisesTest.java    Your exercises
    └── ExerciseSolutionsTest.java
src/test/resources/features/login.feature
```

## 4. Run the tests

```bash
mvn test                              # everything
mvn test -Dtest=NavigationTest        # one class
mvn test -Dtest=RunCucumber           # Cucumber scenarios only
```

Or click the green arrow next to a class or test in IntelliJ.

Cucumber report: open `target/cucumber-reports/cucumber.html` after a run. Failed scenarios include a screenshot.

## 5. Choose a browser

```bash
mvn test -Dbrowser=firefox            # chrome (default), firefox, edge, safari
```

The browser must be installed. For Safari (macOS only), first run `safaridriver --enable` once and turn on **Develop → Allow Remote Automation** in Safari.

## 6. Run on Selenium Grid

Add `-Dgrid.url`. The browser opens on the Grid instead of your machine.

**Grid on your own machine** (needs Java only):

```bash
curl -LO https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.51.0/selenium-server-4.51.0.jar
java -jar selenium-server-4.51.0.jar standalone --selenium-manager true
```

Leave that running, then in another terminal:

```bash
mvn test -Dgrid.url=http://localhost:4444 -Dbrowser=firefox
```

Open `http://localhost:4444/ui` to see the Grid.

**Class Grid:** use the URL, username and password your instructor gives you.

```bash
export GRID_USER=student
export GRID_PASS='<password from class>'
mvn test -Dgrid.url=http://<grid-ip>:4444 -Dbrowser=chrome
```

On Windows PowerShell use `$env:GRID_USER="student"` and `$env:GRID_PASS="..."` instead of `export`.

## 7. Exercises

1. Open `basics/ExercisesTest.java`.
2. Delete the `@Disabled` line above the class.
3. Replace each `fail("TODO")` with your code and run `mvn test -Dtest=ExercisesTest`.
4. Compare with `ExerciseSolutionsTest.java` when you're done.

## Practice sites

- https://the-internet.herokuapp.com
- https://www.saucedemo.com: users `standard_user` and `locked_out_user`, password `secret_sauce`

## Troubleshooting

| Problem | Fix |
| --- | --- |
| `mvn -v` shows a Java version other than 21 | Set `JAVA_HOME` to your JDK 21 folder and open a new terminal |
| `release version 21 not supported` | Same as above; in IntelliJ also set the project SDK to 21 |
| `SessionNotCreatedException` | Update the browser, check your internet, run again |
| `NoSuchElementException` | Check the locator in Chrome DevTools (Ctrl+F in Elements); add a wait |
| Firefox won't start on Ubuntu | The snap Firefox doesn't work with Selenium: use Chrome |
| `401` from the Grid | `GRID_USER` / `GRID_PASS` not set in this terminal |
