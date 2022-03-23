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

    @NonNull private String nss;

    @NonNull private Banque banque;

    public PatientDTO(@NonNull UUID id, @NotNull String prenom, @NotNull String nom, @NonNull Adresse adresse, @NonNull String nss, @NonNull Banque banque, @NonNull UserRole role) {
        super(id, prenom, nom, role);
        this.adresse = adresse;
        this.nss = nss;
        this.banque = banque;
    }
}
