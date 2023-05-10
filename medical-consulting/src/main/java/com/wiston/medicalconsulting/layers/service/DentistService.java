package com.wiston.medicalconsulting.layers.service;

import com.wiston.medicalconsulting.dto.ScheduleDTO;
import com.wiston.medicalconsulting.entity.Dentist;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface DentistService {

    Dentist getDentist(String registrationNumber);
    List<Dentist> getAllDentists();
    List<Dentist> getDentistsAvailableOnSpecificSchedule(LocalDateTime schedule);
    Dentist saveDentist(Dentist dentist);
    void deleteDentist(String registrationNumber);
    void deleteDentist(Long dentistId);
}
