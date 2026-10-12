"""Worked example: browser, locators, interactions and waits together."""
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC


def test_standard_user_can_add_product_to_cart(driver, wait):
    driver.get("https://www.saucedemo.com")

    driver.find_element(By.CSS_SELECTOR, "[data-test='username']").send_keys("standard_user")
    driver.find_element(By.CSS_SELECTOR, "[data-test='password']").send_keys("secret_sauce")
    driver.find_element(By.CSS_SELECTOR, "[data-test='login-button']").click()

    wait.until(EC.url_contains("inventory.html"))
    wait.until(EC.presence_of_all_elements_located((By.CLASS_NAME, "inventory_item")))

    products = driver.find_elements(By.CLASS_NAME, "inventory_item")
    assert len(products) == 6

    first_product = products[0]
    name = first_product.find_element(By.CLASS_NAME, "inventory_item_name").text
    first_product.find_element(By.TAG_NAME, "button").click()

    badge = wait.until(EC.visibility_of_element_located((By.CLASS_NAME, "shopping_cart_badge")))
    assert badge.text == "1"
    print(f"Added: {name}")
