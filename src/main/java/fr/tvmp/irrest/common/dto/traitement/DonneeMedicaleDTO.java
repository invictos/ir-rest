package fr.tvmp.irrest.common.dto.traitement;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class DonneeMedicaleDTO {
    @NonNull UUID id;
    @NonNull DonneeMedicaleType type;
    @NonNull String value;
}