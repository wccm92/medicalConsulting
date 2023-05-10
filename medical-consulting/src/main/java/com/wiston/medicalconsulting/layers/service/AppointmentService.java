package com.wiston.medicalconsulting.layers.service;

import com.wiston.medicalconsulting.dto.AppointmentDTO;
import com.wiston.medicalconsulting.entity.Appointment;

import java.util.List;

public interface AppointmentService {

    Appointment getAppointment(Long id);
    List<Appointment> getAllAppointments();
    Appointment scheduleAppointment(AppointmentDTO appointmentDTO);
    Appointment updateAppointment(AppointmentDTO appointmentDTO, Long id);
    void deleteAppointment(Long id);
}
