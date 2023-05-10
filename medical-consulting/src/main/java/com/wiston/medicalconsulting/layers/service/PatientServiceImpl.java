package com.wiston.medicalconsulting.layers.service;

import com.wiston.medicalconsulting.entity.Patient;
import com.wiston.medicalconsulting.exception.PatientAlreadyExistException;
import com.wiston.medicalconsulting.exception.PatientNotFoundException;
import com.wiston.medicalconsulting.layers.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient getPatient(String dni) {
        Optional<Patient> patientOptional = patientRepository.findByDni(dni);
        return safetyUnwraper(patientOptional, dni);
    }

    @Override
    public List<Patient> getAllPatients() {
        return (List<Patient>) patientRepository.findAll();
    }

    @Override
    public Patient savePatient(Patient patient) {
        if (mainEntityIsPresent(patient)) {
            throw new PatientAlreadyExistException(patient.getDni());
        }
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(String dni) {
        patientRepository.deleteByDni(dni);
    }

    @Override
    public void deletePatient(Long patientId) {
        patientRepository.deleteById(patientId);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return null;
    }

    static Patient safetyUnwraper(Optional<Patient> entity, String dni) {
        if(entity.isPresent()) {
            return entity.get();
        } else {
            throw new PatientNotFoundException(dni);
        }
    }

    public boolean mainEntityIsPresent(Patient patient) {
        return patientRepository.findByDni(patient.getDni()).isPresent();
    }
}
