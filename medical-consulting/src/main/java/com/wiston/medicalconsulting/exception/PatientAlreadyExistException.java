package com.wiston.medicalconsulting.exception;

public class PatientAlreadyExistException extends RuntimeException {

    public PatientAlreadyExistException(String dni) {
        super("The patient with the dni number '" + dni + "' already exists");
    }
}
