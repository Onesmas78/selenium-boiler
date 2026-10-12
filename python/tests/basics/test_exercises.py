"""Your exercises. Delete the pytestmark line, then replace each pytest.fail with your code.

Run: pytest tests/basics/test_exercises.py
Answers are in test_exercise_solutions.py; try first.
"""
import pytest

pytestmark = pytest.mark.skip(reason="Delete this line when you start the exercises")


def test_ex1_navigation(driver):
    """Open the-internet home page, click "Form Authentication" (By.LINK_TEXT),
    assert the URL ends with "/login", go back, assert the title is "The Internet"."""
    pytest.fail("TODO")


def test_ex2_three_locators_for_password(driver):
    """On /login, find the password field three ways (id, CSS, XPath)
    and assert all three return the same element."""
    pytest.fail("TODO")


def test_ex3_invalid_password(driver):
    """On /login, log in as "tomsmith" with a wrong password and assert
    the message contains "Your password is invalid!"."""
    pytest.fail("TODO")


def test_ex4_add_remove_elements(driver):
    """On /add_remove_elements/, click "Add Element" 3 times, assert 3 "Delete"
    buttons, click one, assert 2 remain."""
    pytest.fail("TODO")


def test_ex5_wait_for_hidden_element(driver, wait):
    """On /dynamic_loading/1, click Start and wait until "Hello World!" is visible."""
    pytest.fail("TODO")


def test_ex6_locked_out_user(driver):
    """On saucedemo.com, log in as "locked_out_user" / "secret_sauce" and assert
    the error mentions "locked out"."""
    pytest.fail("TODO")
