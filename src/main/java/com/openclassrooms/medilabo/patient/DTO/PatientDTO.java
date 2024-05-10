package com.openclassrooms.medilabo.patient.DTO;

import com.openclassrooms.medilabo.patient.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    int id;
    String prenom;
    String nom;
    String dateNaissance;
    Genre genre;
    String adressePostale;
    String numeroTelephone;

}
