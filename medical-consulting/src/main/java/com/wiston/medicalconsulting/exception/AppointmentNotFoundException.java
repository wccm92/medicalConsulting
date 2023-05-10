package com.wiston.medicalconsulting.exception;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException(Long id) {
        super("The appointment '" + id + "' does not exist in our records");
    }
}
