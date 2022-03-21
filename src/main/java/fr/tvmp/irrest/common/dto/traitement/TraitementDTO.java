package fr.tvmp.irrest.common.dto.traitement;

import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Data
public class TraitementDTO {
    @NonNull UUID id;
    @NonNull UUID medecin;
    @NonNull UUID patient;
    @NonNull List<DonneeDTO> donnees;
}
