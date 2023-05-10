package com.wiston.medicalconsulting.layers.web;

import com.wiston.medicalconsulting.dto.ScheduleDTO;
import com.wiston.medicalconsulting.entity.Dentist;
import com.wiston.medicalconsulting.layers.service.DentistService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/dentist")
public class DentistController {

    @Autowired
    DentistService dentistService;

    @GetMapping("/all")
    public ResponseEntity<List<Dentist>> getAllDentist() {
        return new ResponseEntity<>(dentistService.getAllDentists(), HttpStatus.OK);
    }

    @GetMapping("/allFree")
    public ResponseEntity<List<Dentist>> getAllDentistFree(@RequestBody ScheduleDTO scheduleDTO) {
        return new ResponseEntity<>(dentistService.getDentistsAvailableOnSpecificSchedule(scheduleDTO.getSchedule()), HttpStatus.OK);
    }

    @GetMapping("/registration/{registryId}")
    public ResponseEntity<Dentist> getDentist(@PathVariable String registryId) {
        return new ResponseEntity<>(dentistService.getDentist(registryId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dentist> saveDentist(@RequestBody Dentist dentist) {
        return new ResponseEntity<>(dentistService.saveDentist(dentist), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDentist(@PathVariable Long dentistId) {
        dentistService.deleteDentist(dentistId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/registration/{registryId}")
    public ResponseEntity<HttpStatus> deleteDentist(@PathVariable String registryId) {
        dentistService.deleteDentist(registryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
