@SmokeTests
Feature: A happy holidaymaker



  Scenario:A happy holidaymaker
    Given I like to surf in any of 2 beaches 10 of Sydney
    When I look up the the weather forecast for the next 3 month with POSTAL CODES 2000
    And I only like to surf on "Monday" & "Friday" in next 3 months
    Then I check to if see the temperature is between <12 and 30>
    And I check wind speed to be between 3 and 9
    And I check to see if UV index is <= 12
    And I Pick best suitable spot out of top two spots, based upon suitable weather forecast for the day
