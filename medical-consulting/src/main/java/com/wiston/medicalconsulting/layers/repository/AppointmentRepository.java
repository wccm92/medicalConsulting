package com.wiston.medicalconsulting.layers.repository;

import com.wiston.medicalconsulting.entity.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}
