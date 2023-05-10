package com.wiston.medicalconsulting.layers.service;

import com.wiston.medicalconsulting.dto.AppointmentDTO;
import com.wiston.medicalconsulting.entity.Appointment;
import com.wiston.medicalconsulting.exception.AppointmentNotFoundException;
import com.wiston.medicalconsulting.exception.BusyDentistException;
import com.wiston.medicalconsulting.layers.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    DentistService dentistService;
    @Autowired
    PatientService patientService;

    @Override
    public Appointment getAppointment(Long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        return safetyUnwraper(appointmentOptional, id);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return (List<Appointment>) appointmentRepository.findAll();
    }

    @Override
    public Appointment scheduleAppointment(AppointmentDTO appointmentDTO) {
        if (busyDentist(appointmentDTO.getRegistrationNumber(), appointmentDTO.getAppointmentDate())) {
            throw new BusyDentistException(appointmentDTO.getRegistrationNumber());
        } else {
            Appointment appointment = new Appointment();
            appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
            appointment.setDentist(dentistService.getDentist(appointmentDTO.getRegistrationNumber()));
            appointment.setPatient(patientService.getPatient(appointmentDTO.getPatientDni()));
            return appointmentRepository.save(appointment);
        }
    }

    @Override
    public Appointment updateAppointment(AppointmentDTO appointmentDTO, Long id) {
        Appointment appointment = getAppointment(id);
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setDentist(dentistService.getDentist(appointmentDTO.getRegistrationNumber()));
        appointment.setPatient(patientService.getPatient(appointmentDTO.getPatientDni()));
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {

    }

    static Appointment safetyUnwraper(Optional<Appointment> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        } else {
            throw new AppointmentNotFoundException(id);
        }
    }

    public boolean busyDentist(String registrationNumber, LocalDateTime dateTime) {
        Set<Appointment> appointmentSet = dentistService.getDentist(registrationNumber).getAppointmentSet();
        return appointmentSet.stream().anyMatch(appointment -> appointment.getAppointmentDate().equals(dateTime));
    }
}
