package fr.tvmp.irrest.common.dto.utilisateur;

import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class UserPublicDTO implements CPAMDto {
    @NonNull private UUID id;
    @NonNull private String prenom;
    @NonNull private String nom;
}
