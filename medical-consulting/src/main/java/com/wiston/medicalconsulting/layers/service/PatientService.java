package com.wiston.medicalconsulting.layers.service;

import com.wiston.medicalconsulting.entity.Patient;

import java.util.List;

public interface PatientService {

    Patient getPatient(String dni);
    List<Patient> getAllPatients();
    Patient savePatient(Patient patient);
    Patient updatePatient(Patient patient);
    void deletePatient(String dni);
    void deletePatient(Long patientId);
}
