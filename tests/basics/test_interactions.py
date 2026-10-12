"""Part 3: interacting with elements and reading their state."""
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys


def test_login_with_valid_credentials(driver):
    driver.get("https://the-internet.herokuapp.com/login")

    username = driver.find_element(By.ID, "username")
    username.clear()
    username.send_keys("tomsmith")
    assert username.get_property("value") == "tomsmith"

    driver.find_element(By.ID, "password").send_keys("SuperSecretPassword!", Keys.ENTER)

    message = driver.find_element(By.ID, "flash").text
    assert "You logged into a secure area!" in message
    assert driver.current_url.endswith("/secure")


def test_toggle_checkboxes(driver):
    driver.get("https://the-internet.herokuapp.com/checkboxes")
    boxes = driver.find_elements(By.CSS_SELECTOR, "#checkboxes input")

    first = boxes[0]
    assert not first.is_selected()  # unchecked by default
    first.click()
    assert first.is_selected()

    second = boxes[1]
    assert second.is_selected()  # checked by default


def test_read_attributes_and_state(driver):
    driver.get("https://the-internet.herokuapp.com/login")
    login_btn = driver.find_element(By.CSS_SELECTOR, "button[type='submit']")

    assert login_btn.get_dom_attribute("type") == "submit"
    assert login_btn.tag_name == "button"
    assert login_btn.is_displayed()
    assert login_btn.is_enabled()
