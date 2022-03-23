package fr.tvmp.irrest.common.dto.utilisateur;

import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.common.Banque;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Data
@EqualsAndHashCode(callSuper = true)
public class PatientDTO extends UserDTO{
    @NonNull private Adresse adresse;

    @NonNull private Integer nss;

    @NonNull private Banque banque;

    public PatientDTO(@NonNull UUID id, @NotNull String prenom, @NotNull String nom, @NonNull Adresse adresse, @NonNull Integer nss, @NonNull Banque banque) {
        super(id, prenom, nom);
        this.adresse = adresse;
        this.nss = nss;
        this.banque = banque;
    }
}
