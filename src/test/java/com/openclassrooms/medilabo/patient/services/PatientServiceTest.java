package com.openclassrooms.medilabo.patient.services;

import com.openclassrooms.medilabo.patient.DTO.PatientDTO;
import com.openclassrooms.medilabo.patient.models.Genre;
import com.openclassrooms.medilabo.patient.models.Patient;
import com.openclassrooms.medilabo.patient.repository.PatientRepository;
import com.openclassrooms.medilabo.patient.services.Impl.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = PatientService.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @InjectMocks
    PatientServiceImpl patientService;

    @Mock
    PatientRepository patientRepository;

    @Test
    public void test_getAllPatients() {

        //GIVEN
        List<Patient> expectedPatients = Arrays.asList(
                new Patient(1, "Joker", "Clown", "01-01-2001", Genre.M, "Address", "0612345678"),
                new Patient(2, "Harley", "Queen", "02-02-2002", Genre.F, "Address", "0912345678")
        );
        when(patientRepository.findAll()).thenReturn(expectedPatients);
        List<PatientDTO> expectedPatientsDTO = expectedPatients.stream()
                .map(patient -> {
                    PatientDTO patientDTO = new PatientDTO();
                    BeanUtils.copyProperties(patient, patientDTO);
                    return patientDTO;
                })
                .collect(Collectors.toList());

        //WHEN
        List<PatientDTO> actualPatients = patientService.getAllPatients();

        //THEN
        assertEquals(expectedPatientsDTO, actualPatients);
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    public void test_getPatient() {
        //GIVEN
        int patientId = 1;
        Patient expectedPatient = new Patient(1, "Joker", "Clown", "01-01-2001", Genre.M, "Address", "0612345678");
        when(patientRepository.findById((long) patientId)).thenReturn(Optional.of(expectedPatient));

        PatientDTO expectedPatientDTO = new PatientDTO();
        BeanUtils.copyProperties(expectedPatient, expectedPatientDTO);

        //WHEN
        PatientDTO actualPatient = patientService.getPatientById(patientId);

        //THEN
        assertEquals(expectedPatientDTO, actualPatient);
        verify(patientRepository, times(1)).findById((long) patientId);
    }

    @Test
    public void test_savePatient() {
        //GIVEN
        Patient savePatient = new Patient(1, "Joker", "Clown", "01-01-2001", Genre.M, "Address", "0612345678");
        when(patientRepository.save(savePatient)).thenReturn(savePatient);

        PatientDTO savePatientDTO = new PatientDTO();
        BeanUtils.copyProperties(savePatient, savePatientDTO);

        //WHEN
        Patient savedPatient = patientService.savePatient(savePatientDTO);

        //THEN
        assertEquals(savePatient, savedPatient);
        verify(patientRepository, times(1)).save(savePatient);
    }

    @Test
    public void test_updatePatient(){
        //GIVEN
        Patient updatePatient = new Patient(1, "Joker", "Clown", "01-01-2001", Genre.M, "Address", "0612345678");
        when(patientRepository.save(updatePatient)).thenReturn(updatePatient);

        //WHEN
        Patient updatedPatient = patientService.updatePatient(updatePatient);

        //THEN
        assertEquals(updatePatient, updatedPatient);
        verify(patientRepository, times(1)).save(updatePatient);
    }

    @Test
    public void test_deletePatient() {
        //GIVEN
        int patientId = 1;
        Patient deletePatient = new Patient(1, "Joker", "Clown", "01-01-2001", Genre.M, "Address", "0612345678");
        when(patientRepository.findById((long) patientId)).thenReturn(Optional.of(deletePatient));

        //WHEN
        patientService.deletePatient(patientId);
        Patient deletedPatient = new Patient(0, null, null, null, null, null, null);

        //THEN
        verify(patientRepository, times(1)).delete(deletedPatient);
    }

}

