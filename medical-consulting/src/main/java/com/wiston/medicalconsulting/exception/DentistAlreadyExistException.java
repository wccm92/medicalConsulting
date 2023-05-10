package com.wiston.medicalconsulting.exception;

public class DentistAlreadyExistException extends RuntimeException {

    public DentistAlreadyExistException(String registryId) {
        super("The dentist with the registration number '" + registryId + "' already exists");
    }
}
