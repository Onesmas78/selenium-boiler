"""Part 2: locating elements."""
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with


def test_locate_login_page_elements(driver):
    driver.get("https://the-internet.herokuapp.com/login")

    heading = driver.find_element(By.TAG_NAME, "h2")
    username = driver.find_element(By.ID, "username")
    password = driver.find_element(By.NAME, "password")
    login_btn = driver.find_element(By.CSS_SELECTOR, "button[type='submit']")
    same_btn = driver.find_element(By.XPATH, "//button[contains(.,'Login')]")

    assert heading.text == "Login Page"
    assert username.is_displayed() and password.is_displayed()
    assert login_btn == same_btn  # two locators, same element


def test_link_text_locators(driver):
    driver.get("https://the-internet.herokuapp.com/login")

    exact = driver.find_element(By.LINK_TEXT, "Elemental Selenium")
    partial = driver.find_element(By.PARTIAL_LINK_TEXT, "Elemental")
    assert exact == partial


def test_find_elements_returns_list_or_empty(driver):
    driver.get("https://the-internet.herokuapp.com/checkboxes")

    boxes = driver.find_elements(By.CSS_SELECTOR, "#checkboxes input")
    assert len(boxes) == 2

    # No match: empty list, no exception
    assert driver.find_elements(By.ID, "does-not-exist") == []


def test_relative_locator(driver):
    driver.get("https://the-internet.herokuapp.com/login")

    password_field = driver.find_element(
        locate_with(By.TAG_NAME, "input").below({By.ID: "username"}))
    assert password_field.get_dom_attribute("id") == "password"
