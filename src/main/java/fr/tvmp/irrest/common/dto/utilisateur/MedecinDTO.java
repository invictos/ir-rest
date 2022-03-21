package fr.tvmp.irrest.common.dto.utilisateur;

import fr.tvmp.irrest.common.Adresse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class MedecinDTO extends UserDTO{
    @NonNull private Adresse adresse;

    @NonNull private MedecinType type;

    @NonNull private String siret;
}
