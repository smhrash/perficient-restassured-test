package com.perficient.sarker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookingDatesDTO {

    @JsonProperty("checkin")
    private Date checkin;

    @JsonProperty("checkout")
    private Date checkout;
}
