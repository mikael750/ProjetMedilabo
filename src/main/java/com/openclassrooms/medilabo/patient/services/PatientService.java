package com.openclassrooms.medilabo.patient.services;

import com.openclassrooms.medilabo.patient.DTO.PatientDTO;
import com.openclassrooms.medilabo.patient.models.Patient;

import java.text.ParseException;
import java.util.List;

public interface PatientService {

    List<PatientDTO> getAllPatients();

    PatientDTO getPatientById(int id);

    Patient savePatient(PatientDTO patientDTO) throws ParseException;

    Patient updatePatient(Patient patient);

    void deletePatient(int id);

}
