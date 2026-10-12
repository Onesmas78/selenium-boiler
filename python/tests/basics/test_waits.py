"""Part 4: waits and synchronisation."""
import pytest
from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait


@pytest.mark.skip(reason="Demo of a failing test without a wait: remove this line to show it")
def test_fails_without_wait(driver):
    driver.get("https://the-internet.herokuapp.com/dynamic_loading/2")
    driver.find_element(By.CSS_SELECTOR, "#start button").click()

    assert driver.find_element(By.ID, "finish").text == "Hello World!"


def test_waits_for_dynamic_content(driver, wait):
    driver.get("https://the-internet.herokuapp.com/dynamic_loading/2")
    driver.find_element(By.CSS_SELECTOR, "#start button").click()

    finish = wait.until(EC.visibility_of_element_located((By.ID, "finish")))
    assert finish.text == "Hello World!"


def test_waits_for_spinner_to_disappear(driver, wait):
    driver.get("https://the-internet.herokuapp.com/dynamic_loading/1")
    driver.find_element(By.CSS_SELECTOR, "#start button").click()

    wait.until(EC.invisibility_of_element_located((By.ID, "loading")))
    assert driver.find_element(By.ID, "finish").text == "Hello World!"


def test_fluent_wait_example(driver):
    driver.get("https://the-internet.herokuapp.com/dynamic_loading/2")
    driver.find_element(By.CSS_SELECTOR, "#start button").click()

    fluent = WebDriverWait(driver, timeout=15, poll_frequency=0.25,
                           ignored_exceptions=[NoSuchElementException])
    result = fluent.until(lambda d: d.find_element(By.ID, "finish"))
    assert result.text == "Hello World!"
