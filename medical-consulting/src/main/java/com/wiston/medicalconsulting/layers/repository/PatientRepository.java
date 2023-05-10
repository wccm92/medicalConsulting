package com.wiston.medicalconsulting.layers.repository;

import com.wiston.medicalconsulting.entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    Optional<Patient> findByDni(String dni);
    @Transactional
    void deleteByDni(String dni);
}
