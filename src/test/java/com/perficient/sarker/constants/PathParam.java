package com.perficient.sarker.constants;

public enum PathParam {
    CREATE_TOKEN("/auth"),
    CREATE_BOOKING("/booking"),
    GET_BOOKINGS("/booking"),
    GET_BOOKING("/booking/"),
    UPDATE_BOOKING("/booking/"),
    PATCH_BOOKING("/booking/"),
    DELETE_BOOKING("/booking/"),
    HEALTH_CHECK("/ping");


    private String param;
    PathParam(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }
}
