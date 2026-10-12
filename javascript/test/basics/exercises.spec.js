// Your exercises. Change describe.skip to describe, then replace each assert.fail with your code.
// Run: npx mocha test/basics/exercises.spec.js
// Answers are in exerciseSolutions.spec.js; try first.
const assert = require('node:assert/strict');
const { useBrowser } = require('../support/browser');

describe.skip('Exercises', function () {
  const browser = useBrowser();

  // 1. Open the-internet home page, click "Form Authentication" (By.linkText),
  //    check the URL ends with "/login", go back, check the title is "The Internet".
  it('ex1 navigation', async function () {
    assert.fail('TODO');
  });

  // 2. On /login, find the password field three ways (id, CSS, XPath)
  //    and check all three are the same element.
  it('ex2 three locators for password', async function () {
    assert.fail('TODO');
  });

  // 3. On /login, log in as "tomsmith" with a wrong password and check
  //    the message contains "Your password is invalid!".
  it('ex3 invalid password', async function () {
    assert.fail('TODO');
  });

  // 4. On /add_remove_elements/, click "Add Element" 3 times, check there are 3
  //    "Delete" buttons, click one, check 2 remain.
  it('ex4 add and remove elements', async function () {
    assert.fail('TODO');
  });

  // 5. On /dynamic_loading/1, click Start and wait until "Hello World!" is visible.
  it('ex5 wait for hidden element', async function () {
    assert.fail('TODO');
  });

  // 6. On saucedemo.com, log in as "locked_out_user" / "secret_sauce" and check
  //    the error mentions "locked out".
  it('ex6 locked out user', async function () {
    assert.fail('TODO');
  });
});
