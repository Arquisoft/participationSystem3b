Feature: Being able to login
Scenario: Login
  Login with some username

    Given the user is on the login page
    When I login with name "user" and password "user"
    Then I receive a list of suggestions, with "Sugerencia1"