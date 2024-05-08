package com.openclassrooms.medilabo.patient.controllers;

import com.openclassrooms.medilabo.patient.DTO.PatientDTO;
import com.openclassrooms.medilabo.patient.models.Patient;
import com.openclassrooms.medilabo.patient.services.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/patient")
@Slf4j
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable int id) {
        PatientDTO patientDTO = patientService.getPatientById(id);
        if (patientDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();

        if (patients != null) {
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } else {
            log.error("Method getAllPatient returned null");
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody PatientDTO patientDTO) throws ParseException {

        return new ResponseEntity<>(patientService.savePatient(patientDTO), HttpStatus.CREATED);

    }

    @PostMapping("/{id}/update")
    public ResponseEntity<Patient> updatePatient(@RequestBody PatientDTO patientDTO, @PathVariable("id") int id) throws ParseException {

        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO,patient);
        patient.setId(id);
        return new ResponseEntity<>(patientService.updatePatient(patient), HttpStatus.OK);

    }

    @PostMapping("/{id}/delete")
    public void deletePatient(@PathVariable("id") int id) {

        patientService.deletePatient(id);

    }
}
