package fr.tvmp.irrest.common.dto.traitement;

import lombok.Data;
import lombok.NonNull;

@Data
public class DonneeMedicaleNewDTO {
    @NonNull DonneeMedicaleType type;
    @NonNull String value;
}
