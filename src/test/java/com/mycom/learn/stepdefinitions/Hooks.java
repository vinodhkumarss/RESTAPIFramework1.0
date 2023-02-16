package com.mycom.learn.stepdefinitions;


import com.mycom.learn.utils.Utils;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;

import java.io.IOException;

public class Hooks extends Utils {

    @Before("@DeletePlace")
    public void before_scenario() throws IOException {
        StepDefinition sd = new StepDefinition();
        if(StepDefinition.place_id == null){
            sd.user_can_supply_payload_in_body("Test_1","Test_1","English");
            sd.user_can_submit_request_to_with_http_method("AddPlaceAPI", "POST");
            sd.verify_of_created_place_using("place_id", "Test_1", "GetPlaceAPI");
        }
    }

    @BeforeStep()
    public void before_step(){
        // StepDefinition sd = new StepDefinition();
        // System.out.println(Utils.get_json_value(StepDefinition.response, "place_id"));

    }
}
