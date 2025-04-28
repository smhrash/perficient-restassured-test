package com.perficient.sarker.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com/perficient/sarker/steps"},
        plugin = {
                "pretty",
                "json:target/cucumber-reports.json",
                "rerun:target/rerun.txt"
        },
        monochrome = true
//        tags = "@patch-booking"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}



