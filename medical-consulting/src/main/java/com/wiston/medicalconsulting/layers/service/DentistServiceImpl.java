package com.wiston.medicalconsulting.layers.service;

import com.wiston.medicalconsulting.dto.ScheduleDTO;
import com.wiston.medicalconsulting.entity.Appointment;
import com.wiston.medicalconsulting.entity.Dentist;
import com.wiston.medicalconsulting.exception.DentistAlreadyExistException;
import com.wiston.medicalconsulting.exception.DentistNotFoundException;
import com.wiston.medicalconsulting.layers.repository.DentistRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DentistServiceImpl implements DentistService {

    @Autowired
    DentistRepository dentistRepository;

    @Override
    public Dentist getDentist(String registrationNumber) {
        Optional<Dentist> dentistOptional = dentistRepository.findByRegistrationNumber(registrationNumber);
        return safetyUnwraper(dentistOptional, registrationNumber);
    }

    @Override
    public List<Dentist> getAllDentists() {
        return (List<Dentist>) dentistRepository.findAll();
    }

    @Override
    public List<Dentist> getDentistsAvailableOnSpecificSchedule(LocalDateTime schedule) {
        List<Dentist> dentists = (List<Dentist>) dentistRepository.findAll();
        List<Dentist> availabledentistList = new ArrayList<>();
        for (Dentist dentist : dentists) {
            Set<Appointment> appointmentSetPerDentist = dentist.getAppointmentSet();
            if (appointmentSetPerDentist.stream().noneMatch(appointment -> appointment.getAppointmentDate().equals(schedule))) {
                availabledentistList.add(dentist);
            }
        }
        return availabledentistList;
    }

    @Override
    public Dentist saveDentist(Dentist dentist) {
        if (mainEntityIsPresent(dentist)) {
            throw new DentistAlreadyExistException(dentist.getRegistrationNumber());
        }
        return dentistRepository.save(dentist);
    }

    @Override
    public void deleteDentist(String registrationNumber) {
        dentistRepository.deleteByRegistrationNumber(registrationNumber);
    }

    @Override
    public void deleteDentist(Long dentistId) {
        dentistRepository.deleteById(dentistId);
    }

    static Dentist safetyUnwraper(Optional<Dentist> entity, String registryId) {
        if(entity.isPresent()) {
            return entity.get();
        } else {
            throw new DentistNotFoundException(registryId);
        }
    }

    public boolean mainEntityIsPresent(Dentist dentist) {
        return dentistRepository.findByRegistrationNumber(dentist.getRegistrationNumber()).isPresent();
    }
}
