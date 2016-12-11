Feature: Guinea Pig link

  Scenario: Can follow link to another page
    Given I am on the Guinea Pig homepage
    When I click on the link
    Then I should be on another page
