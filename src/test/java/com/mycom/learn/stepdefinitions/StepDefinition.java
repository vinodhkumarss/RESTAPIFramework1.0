package com.mycom.learn.stepdefinitions;

import com.mycom.learn.data.TestDataBuild;
import com.mycom.learn.utils.APIResources;
import com.mycom.learn.utils.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {
    private RequestSpecification request;

    private ResponseSpecification resBuilder;
    static Response response;

    static String place_id;

    @Given("User can supply payload in body with {string} {string} {string}")
    public void user_can_supply_payload_in_body(String name, String address, String language) throws IOException {
        request = given().spec(request_specification())
                .body(TestDataBuild.add_place(name, address, language));
    }
    @When("User can submit request to {string} with {string} http method")
    public void user_can_submit_request_to_with_http_method(String resource_name, String http_method) {
        APIResources resources = APIResources.valueOf(resource_name);
        // Response Spec Builder
        resBuilder = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
        if (http_method.equalsIgnoreCase("post")){
            response = request.when().post(resources.getResource_name())
                    .then().extract().response();
        } else if (http_method.equalsIgnoreCase("get")) {
            response = request.when().get(resources.getResource_name())
                    .then().spec(resBuilder).extract().response();
        } else if (http_method.equalsIgnoreCase("delete")) {
            response = request.when().delete(resources.getResource_name())
                    .then().extract().response();
        }
    }
    @Then("User should get response with status code = {int}")
    public void user_should_get_response_with_status_code(Integer expectedStatusCode) {
        assertEquals(Integer.valueOf(response.getStatusCode()), expectedStatusCode);
    }
    @And("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        assertEquals(get_json_value(response, key),value);
    }
    @And("Verify {string} of created place {string} using {string}")
    public void verify_of_created_place_using(String key, String expected_name, String resource_name) throws IOException {
        place_id = get_json_value(response, key);
        request = given().spec(request_specification())
                .queryParam(key , place_id);
        user_can_submit_request_to_with_http_method(resource_name, "get");
        assertEquals(get_json_value(response, "name"), expected_name);
    }
    @Given("User can supply payload in body")
    public void user_can_supply_payload_in_body() throws IOException {
        request = given().spec(request_specification())
                .body(TestDataBuild.delete_payload(place_id));
    }
}
