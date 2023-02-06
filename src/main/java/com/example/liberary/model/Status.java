package com.example.liberary.model;

public enum Status {
    CREATED, CANCELED, ACCESS;

    public static Status getStatus(Object orderStatus) {
        if (!(orderStatus instanceof String)) {
            throw new IllegalArgumentException("sessionRole is null");
        }
        String status = (String) orderStatus;
        for (Status b : Status.values()) {
            if (b.name().equalsIgnoreCase(status)) {
                return b;
            }
        }
        throw new IllegalArgumentException("status not found");
    }
}
