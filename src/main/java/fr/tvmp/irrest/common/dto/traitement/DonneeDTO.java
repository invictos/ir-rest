package fr.tvmp.irrest.common.dto.traitement;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class DonneeDTO {
    @NonNull UUID id;
    @NonNull DonneeType type;
    @NonNull String value;
}