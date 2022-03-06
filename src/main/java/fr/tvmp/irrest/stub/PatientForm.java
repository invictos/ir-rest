package fr.tvmp.irrest.stub;

import fr.tvmp.irrest.common.Adresse;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class PatientForm {
    @NotNull private String prenom;
    @NotNull private String nom;
    @NotNull private String password;

    @NotNull private Adresse adresse;
    @NotNull private Integer nss;
}