package com.wiston.medicalconsulting;

import com.wiston.medicalconsulting.entity.Appointment;
import com.wiston.medicalconsulting.entity.Dentist;
import com.wiston.medicalconsulting.entity.Patient;
import com.wiston.medicalconsulting.layers.service.DentistService;
import com.wiston.medicalconsulting.layers.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class MedicalConsultingApplication implements CommandLineRunner {

	@Autowired
	DentistService dentistService;
	@Autowired
	PatientService patientService;

	public static void main(String[] args) {
		SpringApplication.run(MedicalConsultingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Patient[] patients = new Patient[] {
				new Patient("Pedro Castaño", "102"),
				new Patient("Roberto Silva", "103"),
		};

		for (Patient patient1 : patients) {
			patientService.savePatient(patient1);
		}

		Dentist[] dentists = new Dentist[] {
				new Dentist("Beatriz Meriño", "1047451431"),
				new Dentist("Beatriz Mora", "1047451432"),
		};

		for (Dentist dentist : dentists) {
			dentistService.saveDentist(dentist);
		}
	}
}
