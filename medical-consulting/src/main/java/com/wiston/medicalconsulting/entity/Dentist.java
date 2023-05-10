package com.wiston.medicalconsulting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "dentist")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "registration-number", nullable = false)
    private String registrationNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "dentist", cascade = CascadeType.ALL)
    private Set<Appointment> appointmentSet;
}
