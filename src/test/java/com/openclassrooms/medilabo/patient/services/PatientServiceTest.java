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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
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
    public void test_getAllPatients() throws ParseException {

        //GIVEN
        List<Patient> expectedPatients = Arrays.asList(
                new Patient(1, "Joker", "Clown", new SimpleDateFormat("yyyy-MM-dd").parse("01-01-2001"), Genre.M, "Address", "0612345678"),
                new Patient(2, "Harley", "Queen", new SimpleDateFormat("yyyy-MM-dd").parse("02-02-2002"), Genre.F, "Address", "0912345678")
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


}

