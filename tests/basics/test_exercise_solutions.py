"""Answers for test_exercises.py."""
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC


def test_ex1_navigation(driver):
    driver.get("https://the-internet.herokuapp.com")
    driver.find_element(By.LINK_TEXT, "Form Authentication").click()
    assert driver.current_url.endswith("/login")
    driver.back()
    assert driver.title == "The Internet"


def test_ex2_three_locators_for_password(driver):
    driver.get("https://the-internet.herokuapp.com/login")
    by_id = driver.find_element(By.ID, "password")
    by_css = driver.find_element(By.CSS_SELECTOR, "input[type='password']")
    by_xpath = driver.find_element(By.XPATH, "//input[@name='password']")
    assert by_id == by_css == by_xpath


def test_ex3_invalid_password(driver):
    driver.get("https://the-internet.herokuapp.com/login")
    driver.find_element(By.ID, "username").send_keys("tomsmith")
    driver.find_element(By.ID, "password").send_keys("wrongPassword")
    driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()
    assert "Your password is invalid!" in driver.find_element(By.ID, "flash").text


def test_ex4_add_remove_elements(driver):
    driver.get("https://the-internet.herokuapp.com/add_remove_elements/")
    add = driver.find_element(By.XPATH, "//button[text()='Add Element']")
    for _ in range(3):
        add.click()
    deletes = driver.find_elements(By.XPATH, "//button[text()='Delete']")
    assert len(deletes) == 3

    deletes[0].click()
    # Find again: the old list may hold stale elements
    assert len(driver.find_elements(By.XPATH, "//button[text()='Delete']")) == 2


def test_ex5_wait_for_hidden_element(driver, wait):
    driver.get("https://the-internet.herokuapp.com/dynamic_loading/1")
    driver.find_element(By.CSS_SELECTOR, "#start button").click()
    finish = wait.until(EC.visibility_of_element_located((By.ID, "finish")))
    assert finish.text == "Hello World!"


def test_ex6_locked_out_user(driver):
    driver.get("https://www.saucedemo.com")
    driver.find_element(By.ID, "user-name").send_keys("locked_out_user")
    driver.find_element(By.ID, "password").send_keys("secret_sauce")
    driver.find_element(By.ID, "login-button").click()
    error = driver.find_element(By.CSS_SELECTOR, "[data-test='error']").text
    assert "locked out" in error
