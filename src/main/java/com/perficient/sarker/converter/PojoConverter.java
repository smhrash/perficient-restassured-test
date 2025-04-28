package com.perficient.sarker.converter;

import com.perficient.sarker.dto.UserDTO;
import com.perficient.sarker.dto.BookingDTO;
import com.perficient.sarker.dto.BookingDatesDTO;

import java.util.Date;

public class PojoConverter {

    public UserDTO createUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("admin");
        userDTO.setPassword("password123");
        return userDTO;
    }

    public BookingDTO createBooking(String firstname, String lastname, int totalPrice, boolean hasDepositPaid, Date checkin, Date checkout, String additionalNeeds) {
        BookingDTO bookingDTO = new BookingDTO();
        BookingDatesDTO bookingDatesDTO = new BookingDatesDTO();
        bookingDTO.setFirstname(firstname);
        bookingDTO.setLastname(lastname);
        bookingDTO.setTotalPrice(totalPrice);
        bookingDTO.setHasDepositPaid(hasDepositPaid);
        bookingDatesDTO.setCheckin(checkin);
        bookingDatesDTO.setCheckout(checkout);
        bookingDTO.setBookingDates(bookingDatesDTO);
        bookingDTO.setAdditionalNeeds(additionalNeeds);
        return bookingDTO;
    }

    public BookingDTO updateBooking(String firstname, String lastname, int totalPrice, boolean hasDepositPaid, Date checkin, Date checkout, String additionalNeeds) {
        BookingDTO bookingDTO = new BookingDTO();
        BookingDatesDTO bookingDatesDTO = new BookingDatesDTO();
        bookingDTO.setFirstname(firstname);
        bookingDTO.setLastname(lastname);
        bookingDTO.setTotalPrice(totalPrice);
        bookingDTO.setHasDepositPaid(hasDepositPaid);
        bookingDatesDTO.setCheckin(checkin);
        bookingDatesDTO.setCheckout(checkout);
        bookingDTO.setBookingDates(bookingDatesDTO);
        bookingDTO.setAdditionalNeeds(additionalNeeds);
        return bookingDTO;
    }

    public String patchBooking() {

        return "{\n" +
                "    \"firstname\": \"Lotus\",\n" +
                "    \"lastname\": \"Kamal\"\n" +
                "}";
    }
}
