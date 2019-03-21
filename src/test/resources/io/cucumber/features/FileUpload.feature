
Feature: File upload

  @api
  Scenario: Upload button doesn't work with no file attached
    Given I have not attached any file
    When I upload the file
    Then The file upload process fails