"""Part 1: browser commands and navigation."""


def test_navigate_between_pages(driver):
    driver.get("https://the-internet.herokuapp.com")
    assert driver.title == "The Internet"

    driver.get("https://the-internet.herokuapp.com/login")
    assert driver.current_url.endswith("/login")

    driver.back()
    assert driver.current_url == "https://the-internet.herokuapp.com/"

    driver.forward()
    driver.refresh()
    assert driver.current_url.endswith("/login")


def test_set_fixed_window_size(driver):
    driver.set_window_size(1280, 800)
    driver.get("https://the-internet.herokuapp.com")

    size = driver.get_window_size()
    assert size["width"] == 1280
    assert size["height"] == 800
