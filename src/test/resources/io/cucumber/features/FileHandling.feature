@api
Feature: File upload and download

  Scenario: Download file
    Given I have a File
    When I download the file
    Then The file is successfully downloaded

  Scenario: Download correct file type
    Given I have a File
    When I download the file
    Then The file is the correct type