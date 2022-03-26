package fr.tvmp.irrest.common.dto.utilisateur;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "role")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "Patient", value = PatientDTO.class),
        @JsonSubTypes.Type(name = "Medecin", value = MedecinDTO.class),
        @JsonSubTypes.Type(name = "Administratif", value = AdministratifDTO.class)
})
public abstract class UserDTO implements CPAMDto {
    @NonNull private UUID id;
    @NonNull private String prenom;
    @NonNull private String nom;
    @NonNull private UserRole role;
}