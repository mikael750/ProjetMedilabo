package com.openclassrooms.medilabo.patient.DTO;

import com.openclassrooms.medilabo.patient.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    String prenom;
    String nom;
    String dateNaissance;
    Genre genre;
    String adressePostale;
    String numeroTelephone;

}
