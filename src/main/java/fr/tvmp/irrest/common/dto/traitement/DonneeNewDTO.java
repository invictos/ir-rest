package fr.tvmp.irrest.common.dto.traitement;

import lombok.Data;
import lombok.NonNull;

@Data
public class DonneeNewDTO {
    @NonNull DonneeType type;
    @NonNull String value;
}
