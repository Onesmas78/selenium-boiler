"""Creates the browser for every test (Python version of DriverFactory.java).

BROWSER=chrome|firefox|edge|safari      (default chrome)
GRID_URL=http://localhost:4444          (omit to run locally)
GRID_USER / GRID_PASS                   (only for a Grid that needs a login)
"""
import os

from selenium import webdriver
from selenium.webdriver.remote.client_config import ClientConfig

OPTIONS = {
    "chrome": webdriver.ChromeOptions,
    "firefox": webdriver.FirefoxOptions,
    "edge": webdriver.EdgeOptions,
    "safari": webdriver.SafariOptions,
}

LOCAL_DRIVERS = {
    "chrome": webdriver.Chrome,
    "firefox": webdriver.Firefox,
    "edge": webdriver.Edge,
    "safari": webdriver.Safari,
}


def create_driver():
    browser = os.getenv("BROWSER", "chrome").lower()
    if browser not in OPTIONS:
        raise ValueError(f"Unknown browser: {browser}")
    options = OPTIONS[browser]()

    grid_url = os.getenv("GRID_URL", "")
    if not grid_url:
        return LOCAL_DRIVERS[browser](options=options)

    auth = {}
    user, password = os.getenv("GRID_USER"), os.getenv("GRID_PASS")
    if user and password:
        auth = {"username": user, "password": password}
    config = ClientConfig(remote_server_addr=grid_url, **auth)
    return webdriver.Remote(command_executor=grid_url, options=options, client_config=config)
