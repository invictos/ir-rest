package fr.tvmp.irrest.common.dto.utilisateur;

import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.common.Banque;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientNewDTO {
    @NotNull private String prenom;
    @NotNull private String nom;
    @NotNull private String password;

    @NotNull private Adresse adresse;
    @NotNull private Integer nss;
    @NotNull private Banque banque;
}