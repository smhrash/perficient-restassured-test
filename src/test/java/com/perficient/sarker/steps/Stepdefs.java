package com.perficient.sarker.steps;

import com.perficient.sarker.ApiTestBase;
import com.perficient.sarker.constants.PathParam;
import com.perficient.sarker.converter.PojoConverter;
import com.perficient.sarker.dto.BookingDTO;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class Stepdefs {

    private RequestSpecification requestSpecification;
    private ApiTestBase apiTestBase = ApiTestBase.getInstance();

    PojoConverter pojoConverter = new PojoConverter();
    Response response;

    private static String token = "";
    private static String bookingid = "";


    @Given("I have the necessary credentials for authentication")
    public void iHaveTheNecessaryCredentialsForAuthentication() {
        requestSpecification = given().spec(apiTestBase.requestSpec()).body(pojoConverter.createUser());
    }

    @When("I send a {string} request using {string} method")
    public void iSendARequestUsingMethod(String param, String method) {
        PathParam pathParam = PathParam.valueOf(param);
        responseSpecification = new ResponseSpecBuilder().build();
        if (method.equals("POST") && param.equals("CREATE_TOKEN")) {
            response = requestSpecification.when().post(pathParam.getParam());
        } else if (method.equals("POST") && param.equals("CREATE_BOOKING")) {
            response = requestSpecification.when().post(pathParam.getParam());
        } else if (method.equals("GET") && param.equals("GET_BOOKINGS")) {
            response = requestSpecification.when().get(pathParam.getParam());
        } else if (method.equals("GET") && param.equals("GET_BOOKING")) {
            response = requestSpecification.when().get(pathParam.getParam().concat((bookingid)));
        } else if (method.equals("DELETE") && param.equals("DELETE_BOOKING")) {
            response = requestSpecification.when().delete(pathParam.getParam().concat((bookingid)));
        } else if (method.equals("PUT") && param.equals("UPDATE_BOOKING")) {
            response = requestSpecification.when().put(pathParam.getParam().concat((bookingid)));
        } else if (method.equals("PATCH") && param.equals("PATCH_BOOKING")) {
            response = requestSpecification.when().patch(pathParam.getParam().concat((bookingid)));
        } else if (method.equals("GET") && param.equals("HEALTH_CHECK")) {
            response = requestSpecification.when().get(pathParam.getParam());
        }
    }

    @Then("I should receive a response with {int} status code")
    public void iShouldReceiveAResponseWithStatusCode(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @And("the response should include the new auth token")
    public void theResponseShouldIncludeTheNewAuthToken() {
        token = apiTestBase.getResponseValue(response, "token");
        System.out.println("token=" + token);
        assertFalse(token.isBlank());
    }

    @Given("I set the base URL to get booking")
    public void iSetTheBaseURLToGetBooking() {
        requestSpecification = given().spec(apiTestBase.requestSpec());
    }

    @And("the response should contain a list of booking ids")
    public void theResponseShouldContainAListOfBookingIds() {
        assertFalse(apiTestBase.getResponseValue(response, "bookingid").isBlank());
    }

    @Given("I have a valid booking payload with booking details {string} {string} {string} {string} {string} {string} {string}")
    public void iHaveAValidBookingPayloadWithBookingDetails(String firstname, String lastname, String totalPrice, String hasDepositPaid, String checkin, String checkout, String additionalNeeds) throws ParseException {
        // Set request specification with the booking payload
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date checkinDate = dateFormat.parse(checkin);
        Date checkoutDate = dateFormat.parse(checkout);
        requestSpecification = given()
                .spec(apiTestBase.requestSpec())
                .body(pojoConverter.createBooking(firstname, lastname, Integer.parseInt(totalPrice), Boolean.parseBoolean(hasDepositPaid), checkinDate, checkoutDate, additionalNeeds));
    }

    @And("the response should contain a booking id")
    public void theResponseShouldContainABookingId() {
        bookingid = apiTestBase.getResponseValue(response, "bookingid");
        assertFalse(bookingid.isBlank());
    }

    @Given("I set the base URL and auth token to delete booking")
    public void iSetTheBaseURLAndAuthTokenToDeleteBooking() {
        requestSpecification = given().spec(apiTestBase.requestSpec()).cookie("token", token);
    }

    @Given("I have updated booking payload {string} {string} {string} {string} {string} {string} {string}")
    public void iHaveUpdatedBookingPayload(String firstname, String lastname, String totalPrice, String hasDepositPaid, String checkin, String checkout, String additionalNeeds) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date checkinDate = dateFormat.parse(checkin);
        Date checkoutDate = dateFormat.parse(checkout);
        requestSpecification = given()
                .spec(apiTestBase.requestSpec())
                .cookie("token", token)
                .body(pojoConverter.updateBooking(firstname, lastname, Integer.parseInt(totalPrice), Boolean.parseBoolean(hasDepositPaid), checkinDate, checkoutDate, additionalNeeds));
    }

    @Given("I have patch booking payload")
    public void iHavePatchedBookingPayload() {
        requestSpecification = given()
                .spec(apiTestBase.requestSpec())
                .cookie("token", token)
                .body(pojoConverter.patchBooking());
    }

    /**
     * Converts Cucumber DataTable to a BookingDTO object.
     *
     * @param dataTable The Cucumber DataTable containing booking details.
     * @return A fully populated BookingDTO object.
     */
    public BookingDTO convertDataTableToBookingDTO(DataTable dataTable,
                                                   String firstname,
                                                   String lastname,
                                                   int totalPrice,
                                                   boolean hasDepositPaid,
                                                   String additionalNeeds,
                                                   Date checkin,
                                                   Date checkout) {
        // Parse dataTable if provided
        if (dataTable != null) {
            List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
            if (!data.isEmpty()) {
                Map<String, String> row = data.get(0); // Take the first row

                // Use provided parameters only if they are null/uninitialized
                if (firstname == null) firstname = row.get("firstname");
                if (lastname == null) lastname = row.get("lastname");
                if (totalPrice == 0) totalPrice = Integer.parseInt(row.get("totalprice"));
                if (additionalNeeds == null) additionalNeeds = row.get("additionalneeds");
                hasDepositPaid = Boolean.parseBoolean(row.get("depositpaid"));

                // Convert date strings to Date objects if not provided
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    if (checkin == null) checkin = dateFormat.parse(row.get("checkin"));
                    if (checkout == null) checkout = dateFormat.parse(row.get("checkout"));
                } catch (ParseException e) {
                    throw new RuntimeException("Error parsing date values from DataTable", e);
                }
            }
        }

        // Create and return BookingDTO
        return pojoConverter.createBooking(firstname, lastname, totalPrice, hasDepositPaid, checkin, checkout, additionalNeeds);
    }


}
