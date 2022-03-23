package fr.tvmp.irrest.common.dto.utilisateur;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdministratifDTO extends UserDTO{
    @NonNull private Integer echelon;

    public AdministratifDTO(@NonNull UUID id, @NotNull String prenom, @NotNull String nom, @NonNull Integer echelon) {
        super(id, prenom, nom);
        this.echelon = echelon;
    }
}
