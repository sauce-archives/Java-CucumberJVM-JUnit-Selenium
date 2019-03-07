Feature: File upload and download

  Scenario: Download file
    Given I have an Excel File
    When I download the file
    Then The file is successfully downloaded