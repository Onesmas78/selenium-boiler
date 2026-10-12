# selenium-boiler: Python

The course tests from `main`, written in Python with pytest. Each Java class has a matching file, so you can compare them side by side.

| Java (`main`) | Python (`python/`) |
| --- | --- |
| JUnit | pytest |
| Cucumber | pytest-bdd (same `login.feature`) |
| `BaseTest.java` | `driver` and `wait` fixtures in `conftest.py` |
| `DriverFactory.java` | `drivers.py` |
| Cucumber HTML report | pytest-html report |

## 1. Install

- Python 3.10 or newer: `python3 --version` (on Windows: `py --version`)
- Google Chrome

## 2. Set up

```bash
git clone https://github.com/Onesmas78/selenium-boiler.git
cd selenium-boiler
git checkout python
cd python

python3 -m venv .venv
source .venv/bin/activate          # Windows: .venv\Scripts\activate
pip install -r requirements.txt
```

Activate the virtual environment (`source .venv/bin/activate`) every time you open a new terminal.

## 3. What's inside

```
python/
├── conftest.py               driver + wait fixtures, screenshot on failure
├── drivers.py                Creates the browser (local or Grid)
├── pytest.ini                Report and feature-file settings
├── requirements.txt
└── tests/
    ├── test_first.py         First test from the setup class
    ├── features/login.feature
    ├── steps/test_login.py   Step definitions for login.feature
    └── basics/
        ├── test_navigation.py
        ├── test_locators.py
        ├── test_interactions.py
        ├── test_waits.py
        ├── test_add_to_cart.py
        ├── test_exercises.py
        └── test_exercise_solutions.py
```

A test asks for the browser by naming `driver` as a parameter. pytest opens it before the test and closes it afterwards:

```python
def test_title(driver):
    driver.get("https://the-internet.herokuapp.com")
    assert driver.title == "The Internet"
```

## 4. Run the tests

Run these from the `python` folder:

```bash
pytest                                    # everything
pytest tests/basics/test_navigation.py    # one file
pytest tests/steps                        # feature-file scenarios only
pytest -k checkbox                        # tests whose name contains "checkbox"
```

Report: open `reports/report.html` after a run. Failed tests include a screenshot.

## 5. Choose a browser or use a Grid

Settings come from environment variables:

```bash
BROWSER=firefox pytest                                   # chrome (default), firefox, edge, safari
GRID_URL=http://localhost:4444 BROWSER=firefox pytest    # run on a Grid
```

For the class Grid, also set the login from your instructor:

```bash
export GRID_USER=student
export GRID_PASS='<password from class>'
GRID_URL=http://<grid-ip>:4444 pytest
```

On Windows PowerShell, set each variable first (`$env:BROWSER="firefox"`), then run `pytest`.

See the main README for how to start a Grid on your own machine.

## 6. Exercises

1. Open `tests/basics/test_exercises.py`.
2. Delete the `pytestmark = ...` line.
3. Replace each `pytest.fail("TODO")` with your code.
4. Run `pytest tests/basics/test_exercises.py`, then compare with `test_exercise_solutions.py`.

## Java to Python cheat sheet

| Java | Python |
| --- | --- |
| `driver.findElement(By.id("x"))` | `driver.find_element(By.ID, "x")` |
| `driver.findElements(...)` | `driver.find_elements(...)` |
| `el.sendKeys("a")` / `el.getText()` | `el.send_keys("a")` / `el.text` |
| `driver.getTitle()` / `getCurrentUrl()` | `driver.title` / `driver.current_url` |
| `driver.navigate().back()` | `driver.back()` |
| `el.getDomProperty("value")` | `el.get_property("value")` |
| `wait.until(ExpectedConditions.visibilityOfElementLocated(by))` | `wait.until(EC.visibility_of_element_located((By.ID, "x")))` |
| `assertEquals(a, b)` | `assert a == b` |
| `@Disabled` | `@pytest.mark.skip` |

## Troubleshooting

| Problem | Fix |
| --- | --- |
| `ModuleNotFoundError: No module named 'selenium'` | Activate the virtual environment, then `pip install -r requirements.txt` |
| `fixture 'driver' not found` | Run `pytest` from the `python` folder |
| `SessionNotCreatedException` | Update the browser, check your internet, run again |
| `NoSuchElementException` | Check the locator in Chrome DevTools; add a wait |
