package fr.tvmp.irrest.common.dto.remboursement;

import fr.tvmp.irrest.common.dto.CPAMDto;
import fr.tvmp.irrest.common.dto.utilisateur.AdministratifDTO;
import fr.tvmp.irrest.common.dto.utilisateur.MedecinDTO;
import fr.tvmp.irrest.common.dto.utilisateur.PatientDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "Patient", value = PatientDTO.class),
        @JsonSubTypes.Type(name = "Medecin", value = MedecinDTO.class),
        @JsonSubTypes.Type(name = "Administratif", value = AdministratifDTO.class)
})
public abstract class ARemboursableDTO implements CPAMDto {
    @NonNull Float prix;
    @NonNull Float tauxCPAM;
    @NonNull Float remboursement;
}
