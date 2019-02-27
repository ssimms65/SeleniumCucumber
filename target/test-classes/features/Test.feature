Feature: Go online

  Scenario: I navigate to the Neueda website then to about us then to software delivery
    Given I start the browser
    And I go to url
    Then I select about us
    Then I select careers
    Then I close the browser

  Scenario: Go to Thomas the Tank site, to about, to disney website
    Given I start the browser
    And I go to this url "https://www.thomasandfriends.com/en-gb/"
    Then I click on element "ABOUT"
    And I go to this url "https://disney.co.uk/"
    Then I close the browser

  Scenario: Check given attribute exists and report true or false
    Given I start the browser
    And I go to this url "https://disney.co.uk/"
    Then I select element "SHOP"
    Then I check attribute "{" exists
    Then I click on element "SHOP"

  Scenario Outline: Go to browser
    Given I start the browser
    And I go to this url "http://adminuihost:3000"
    And I enter user <user>
    Then I select password by xpath <password>



    Examples:
      | user             | password |
      | admin@neueda.com | admin    |
      # | sasha | admin  |

