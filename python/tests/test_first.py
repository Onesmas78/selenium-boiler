"""First test from the setup class: opens Chrome directly, no fixtures."""
from selenium import webdriver


def test_home_has_expected_title():
    driver = webdriver.Chrome()
    try:
        driver.maximize_window()
        driver.get("https://www.saucedemo.com")
        assert driver.title == "Swag Labs"
    finally:
        driver.quit()
