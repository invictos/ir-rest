package fr.tvmp.irrest.common.dto.utilisateur;

import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.common.Banque;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;


@Data
@EqualsAndHashCode(callSuper = true)
public class PatientDTO extends UserDTO{
    @NonNull private Adresse adresse;

    @NonNull private Integer nss;

    @NonNull private Banque banque;
}
