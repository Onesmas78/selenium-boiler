Feature: Login

  Scenario: Standard user logs in successfully
    Given the user is on the login page
    When the user logs in with "standard_user" and "secret_sauce"
    Then the products page is displayed

  Scenario: Locked out user sees an error
    Given the user is on the login page
    When the user logs in with "locked_out_user" and "secret_sauce"
    Then an error containing "locked out" is displayed
