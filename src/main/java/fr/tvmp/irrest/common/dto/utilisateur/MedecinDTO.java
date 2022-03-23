package fr.tvmp.irrest.common.dto.utilisateur;

import fr.tvmp.irrest.common.Adresse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class MedecinDTO extends UserDTO{
    @NonNull private Adresse adresse;

    @NonNull private MedecinType type;

    @NonNull private String siret;

    public MedecinDTO(@NonNull UUID id, @NotNull String prenom, @NotNull String nom, @NonNull Adresse adresse, @NonNull MedecinType type, @NonNull String siret, @NonNull UserRole role) {
        super(id, prenom, nom, role);
        this.adresse = adresse;
        this.type = type;
        this.siret = siret;
    }
}
