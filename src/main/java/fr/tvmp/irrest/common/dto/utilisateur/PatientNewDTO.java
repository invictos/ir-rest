package fr.tvmp.irrest.common.dto.utilisateur;

import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.common.Banque;
import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientNewDTO implements CPAMDto {
    @NonNull private String prenom;
    @NonNull
    private String nom;
    @NonNull private String password;

    @NonNull private Adresse adresse;
    @NonNull private String nss;
    @NonNull private Banque banque;
}