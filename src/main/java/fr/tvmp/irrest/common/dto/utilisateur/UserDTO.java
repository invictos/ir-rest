package fr.tvmp.irrest.common.dto.utilisateur;

import lombok.*;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.validation.constraints.NotNull;
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
public abstract class UserDTO {
    @NonNull private UUID id;
    @NotNull private String prenom;
    @NotNull private String nom;
}