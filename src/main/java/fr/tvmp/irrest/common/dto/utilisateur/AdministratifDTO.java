package fr.tvmp.irrest.common.dto.utilisateur;

import lombok.*;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdministratifDTO extends UserDTO{
    @NonNull private Integer echelon;

    public AdministratifDTO(@NonNull UUID id, @NonNull String prenom, @NonNull String nom, @NonNull Integer echelon, @NonNull UserRole role) {
        super(id, prenom, nom, role);
        this.echelon = echelon;
    }
}
