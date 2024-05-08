package com.openclassrooms.medilabo.patient.repository;

import com.openclassrooms.medilabo.patient.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
