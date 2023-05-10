package com.wiston.medicalconsulting.layers.repository;

import com.wiston.medicalconsulting.entity.Dentist;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DentistRepository extends CrudRepository<Dentist, Long> {

    Optional<Dentist> findByRegistrationNumber(String registrationNumber);
    @Transactional
    void deleteByRegistrationNumber(String registrationNumber);
}
