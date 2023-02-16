package com.mycom.learn.cucumber.options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/com/mycom/learn/features"},
        glue = {"com/mycom/learn/stepdefinitions"},
        plugin = "json:target/jsonReports/cucumber-report.json"
)
public class TestRunner {
}
