package com.wiston.medicalconsulting.exception;

public class DentistNotFoundException extends RuntimeException {

    public DentistNotFoundException(String registryId) {
        super("The dentist with the registrationNumber '" + registryId + "' does not exist in our records");
    }
}
