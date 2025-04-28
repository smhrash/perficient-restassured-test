package com.perficient.sarker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDTO {

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("totalprice")
    private Integer totalPrice;

    @JsonProperty("depositpaid")
    private boolean hasDepositPaid;

    @JsonProperty("bookingdates")
    private BookingDatesDTO bookingDates;

    @JsonProperty("additionalneeds")
    private String additionalNeeds;

}
