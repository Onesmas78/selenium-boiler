package com.onesmas.basics;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Your exercises. Remove @Disabled, then replace each fail(...) with your code.
 * Run: mvn test -Dtest=ExercisesTest
 * Answers are in ExerciseSolutionsTest; try first.
 */
@Disabled("Remove this line when you start the exercises")
public class ExercisesTest extends BaseTest {

    /** 1. Open the-internet home page, click "Form Authentication" (By.linkText),
     *  assert the URL ends with "/login", go back, assert the title is "The Internet". */
    @Test
    void ex1_navigation() {
        fail("TODO");
    }

    /** 2. On /login, find the password field three ways (id, CSS, XPath)
     *  and assert all three return the same element. */
    @Test
    void ex2_threeLocatorsForPassword() {
        fail("TODO");
    }

    /** 3. On /login, log in as "tomsmith" with a wrong password and assert
     *  the message contains "Your password is invalid!". */
    @Test
    void ex3_invalidPassword() {
        fail("TODO");
    }

    /** 4. On /add_remove_elements/, click "Add Element" 3 times, assert 3 "Delete"
     *  buttons, click one, assert 2 remain. */
    @Test
    void ex4_addRemoveElements() {
        fail("TODO");
    }

    /** 5. On /dynamic_loading/1, click Start and wait until "Hello World!" is visible. */
    @Test
    void ex5_waitForHiddenElement() {
        fail("TODO");
    }

    /** 6. On saucedemo.com, log in as "locked_out_user" / "secret_sauce" and assert
     *  the error mentions "locked out". */
    @Test
    void ex6_lockedOutUser() {
        fail("TODO");
    }
}
