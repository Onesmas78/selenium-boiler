"""Step definitions for login.feature (Python version of LoginSteps.java).

pytest-bdd turns every scenario in the feature file into a pytest test.
"""
from pytest_bdd import given, parsers, scenarios, then, when
from selenium.webdriver.common.by import By

scenarios("login.feature")


@given("the user is on the login page")
def open_login_page(driver):
    driver.get("https://www.saucedemo.com")


@when(parsers.parse('the user logs in with "{username}" and "{password}"'))
def log_in(driver, username, password):
    driver.find_element(By.ID, "user-name").send_keys(username)
    driver.find_element(By.ID, "password").send_keys(password)
    driver.find_element(By.ID, "login-button").click()


@then("the products page is displayed")
def products_page_is_displayed(driver):
    assert "inventory.html" in driver.current_url


@then(parsers.parse('an error containing "{expected}" is displayed'))
def error_is_displayed(driver, expected):
    error = driver.find_element(By.CSS_SELECTOR, "[data-test='error']").text
    assert expected in error, f"Actual error: {error}"
