package com.perficient.sarker.steps;

import com.perficient.sarker.ApiTestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.testng.log4testng.Logger;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Hooks {

    private static final Logger logger = Logger.getLogger(ApiTestBase.class);
    //Define tags that requires a new booking
    private static final Set<String> BOOKING_TAGS = new HashSet<>(Arrays.asList(
            "@get-booking",
            "@delete-booking",
            "@update-booking",
            "@patch-booking"
    ));

    private static final Set<String> DELETE_BOOKING_TAGS = new HashSet<>(Arrays.asList(
            "@create-booking",
            "@update-booking",
            "@patch-booking",
            "@get-booking"
    ));

    @Before
    public void beforeScenario(Scenario scenario) throws ParseException {
        for (String tag : scenario.getSourceTagNames()) {
            if (BOOKING_TAGS.contains(tag)) {
                createBooking();
            }
        }
        logger.info("Booking has been created successfully");
    }

    @After
    public void afterScenario(Scenario scenario) {
        for (String tag : scenario.getSourceTagNames()) {
            if (DELETE_BOOKING_TAGS.contains(tag)) {
                deleteBooking();
                logger.info("Booking has been deleted successfully");
                logger.info("Clearing memory after scenario....");
                System.gc();
                System.runFinalization();
                logger.info("Memory cleared successfully");
            }
        }
    }

    public void createBooking() throws ParseException {
        Stepdefs stepdefs = new Stepdefs();
        //Create auth token
        stepdefs.iHaveTheNecessaryCredentialsForAuthentication();
        stepdefs.iSendARequestUsingMethod("CREATE_TOKEN", "POST");
        stepdefs.theResponseShouldIncludeTheNewAuthToken();
        stepdefs.iHaveAValidBookingPayloadWithBookingDetails("Sarker", "Rashid", "100", "true", "2025-04-20", "2025-04-21", "breakfast");
        stepdefs.iSendARequestUsingMethod("CREATE_BOOKING", "POST");
        stepdefs.theResponseShouldContainABookingId();
    }

    public void deleteBooking() {
        Stepdefs stepdefs = new Stepdefs();
        stepdefs.iSetTheBaseURLAndAuthTokenToDeleteBooking();
        stepdefs.iSendARequestUsingMethod("DELETE_BOOKING", "DELETE");
    }

}
