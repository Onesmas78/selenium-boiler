"""Shared fixtures (Python version of BaseTest.java)."""
import pytest
from selenium.webdriver.support.ui import WebDriverWait

from drivers import create_driver

try:
    import pytest_html
except ImportError:  # report plugin not installed
    pytest_html = None


@pytest.fixture
def driver():
    drv = create_driver()
    drv.maximize_window()
    yield drv
    drv.quit()


@pytest.fixture
def wait(driver):
    return WebDriverWait(driver, 10)


def pytest_bdd_step_error(request, feature, scenario, step, step_func, step_func_args, exception):
    """Cucumber-style scenarios: grab the screenshot when a step fails."""
    drv = step_func_args.get("driver")
    if drv is not None:
        request.node.failure_screenshot = drv.get_screenshot_as_base64()


@pytest.hookimpl(hookwrapper=True)
def pytest_runtest_makereport(item, call):
    """Attach a screenshot to the HTML report when a test fails."""
    outcome = yield
    report = outcome.get_result()
    if report.when != "call" or not report.failed or pytest_html is None:
        return

    png = getattr(item, "failure_screenshot", None)
    drv = item.funcargs.get("driver")
    if png is None and drv is not None:
        png = drv.get_screenshot_as_base64()
    if png is None:
        return

    extras = getattr(report, "extras", [])
    extras.append(pytest_html.extras.png(png, "Screenshot at failure"))
    report.extras = extras
