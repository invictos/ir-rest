package fr.tvmp.irrest.common.dto.traitement;

import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class DonneeMedicaleDTO implements CPAMDto {
    @NonNull UUID id;
    @NonNull DonneeMedicaleType type;
    @NonNull String value;
}