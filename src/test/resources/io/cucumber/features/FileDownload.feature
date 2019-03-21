@api
Feature: File download

  Scenario: Download file
    Given I have access to a URL that points to my file
    When I download the file
    Then The file is successfully downloaded

  Scenario: Download correct file type
    Given I have access to a URL that points to my file
    When I download the file
    Then The file is the correct type