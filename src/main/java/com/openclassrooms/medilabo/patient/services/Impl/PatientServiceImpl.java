package com.openclassrooms.medilabo.patient.services.Impl;

import com.openclassrooms.medilabo.patient.DTO.PatientDTO;
import com.openclassrooms.medilabo.patient.models.Patient;
import com.openclassrooms.medilabo.patient.repository.PatientRepository;
import com.openclassrooms.medilabo.patient.services.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;


    public List<PatientDTO> getAllPatients(){

        var patients = patientRepository.findAll();
        log.info("Patients from repository: {}", patients);

        return patients.stream()
                .map(patient -> {
                    PatientDTO patientDTO = new PatientDTO();
                    BeanUtils.copyProperties(patient, patientDTO);
                    return patientDTO;
                })
                .collect(Collectors.toList());

    }

    public PatientDTO getPatientById(int id) {

        if (id != 0) {
            Patient patient = patientRepository.findById((long) id).orElse(null);
            if (patient == null) {
                return null;
            }
            PatientDTO patientDTO = new PatientDTO();
            BeanUtils.copyProperties(patient, patientDTO);

            return patientDTO;
        }

        return null;

    }

    public Patient savePatient(PatientDTO patientDTO) {

        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO,patient);
        return patientRepository.save(patient);

    }

    public Patient updatePatient(Patient patient) {

        return patientRepository.save(patient);

    }


    public void deletePatient(int id){

        PatientDTO patientToDelete = this.getPatientById(id);
        Patient patient = new Patient();
        BeanUtils.copyProperties(patient, patientToDelete);

        patientRepository.delete(patient);

    }

}
