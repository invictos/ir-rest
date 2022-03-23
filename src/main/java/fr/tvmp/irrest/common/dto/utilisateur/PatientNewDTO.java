package fr.tvmp.irrest.common.dto.utilisateur;

import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.common.Banque;
import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientNewDTO implements CPAMDto {
    @NotNull private String prenom;
    @NotNull private String nom;
    @NotNull private String password;

    @NotNull private Adresse adresse;
    @NotNull private String nss;
    @NotNull private Banque banque;
}