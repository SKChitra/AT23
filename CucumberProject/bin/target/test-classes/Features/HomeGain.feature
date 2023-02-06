Feature: User is able to search for a zipcode and find home value

  Scenario: 
    Verify placeholder text: Zipcode, title text and button text

    Given user goes to "http://www.homegain.com/homevalues"
    And user verifies if "title text": "Connect with top local real estate agents" is present
    Then user checks "placeholder text": "Zip Code" is present
    Then user checks "button text": "Go" is present
    Then user clicks "zipcode" to send: "95212"
    Then user press "GoButton"
    Then user checks "h3titletext": "What type of Real Estate Info do you need?" is present
    Then user selects Home Values
    Then user press "ContinueBtn"
