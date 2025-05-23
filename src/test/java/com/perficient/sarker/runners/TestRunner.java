package com.perficient.sarker.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

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
        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }
}



