package com.wiston.medicalconsulting.exception;

public class BusyDentistException extends RuntimeException {

    public BusyDentistException(String registrationNumber) {
        super("The dentist with the registration number '" + registrationNumber + "' is not available for this schedule");
    }
}
