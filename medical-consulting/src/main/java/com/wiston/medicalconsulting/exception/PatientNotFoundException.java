package com.wiston.medicalconsulting.exception;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(String dni) {
        super("The patient with the dni '" + dni + "' does not exist in our records");
    }
}
