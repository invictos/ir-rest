package fr.tvmp.irrest.common.dto.utilisateur;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class UserPublicDTO {
    @NonNull private UUID id;
    @NonNull private String prenom;
    @NonNull private String nom;
}
