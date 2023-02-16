Feature: Validate Place API

  @AddPlace @Regression
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given User can supply payload in body with "<name>" "<address>" "<language>"
    When User can submit request to "AddPlaceAPI" with "post" http method
    Then User should get response with status code = 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify "place_id" of created place "<name>" using "GetPlaceAPI"

    Examples:
    |name |address |language|
    |test1|test1   |english |


  @DeletePlace
  Scenario: Verify if delete api is working correctly or not
    Given User can supply payload in body
    When User can submit request to "DeletePlaceAPI" with "post" http method
    Then User should get response with status code = 200
    And "status" in response body is "OK"