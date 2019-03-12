
Feature: File upload

  @api
  Scenario: Upload button doesn't work with no file attached
    Given I have not attached any file
    When I upload the file
    Then The file upload process fails

  @ui
  Scenario: Uploading a file is possible
    Given I have attached a file
    When I upload the file
    Then The file upload process works