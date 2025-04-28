package com.perficient.sarker.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/rerun.txt",
        glue = "com.perficient.sarker.steps",
        plugin = {
                "pretty",
                "json:target/cucumber-reports-failed.json"
        },
        monochrome = true
)

public class FailedTestRunner extends AbstractTestNGCucumberTests {
}
